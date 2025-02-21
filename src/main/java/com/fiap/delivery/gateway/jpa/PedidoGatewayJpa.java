package com.fiap.delivery.gateway.jpa;

import com.fiap.delivery.config.mapper.PedidoMapper;
import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.exception.PedidoNaoEncontradoException;
import com.fiap.delivery.gateway.PedidoGateway;
import com.fiap.delivery.gateway.db.entity.PedidoEntity;
import com.fiap.delivery.gateway.db.repository.PedidoRepository;
import com.fiap.delivery.gateway.queue.IDeliveryQueueGateway;
import com.fiap.delivery.usecase.CriarRegistroEntregaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoGatewayJpa implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final CriarRegistroEntregaUseCase criarRegistroEntregaUseCase;
    private final IDeliveryQueueGateway deliveryQueueGateway;

    @Override
    public List<Pedido> obterPedidosPorCep(String cep) {
        return pedidoRepository.findByCepEntrega(cep)
                .stream()
                .map(PedidoMapper.INSTANCE::toData)
                .toList();
    }

    @Override
    public void salvar(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoMapper.INSTANCE.toEntity(pedido);
        pedidoRepository.saveAndFlush(pedidoEntity);
    }

    @Override
    public void finalizarPedido(Long idPedido, String cpf) {
        Pedido pedido = buscarPedidoOuFalhar(idPedido);
        pedido.setStatus(StatusPedido.FINALIZADO);
        salvarPedidoComStatusAtualizado(pedido);
        enviarParaFilaEPersistirRegistro(pedido, cpf, StatusPedido.FINALIZADO);
        log.info("Pedido finalizado e enviado a fila");
    }

    @Override
    public void atualizarPedido(Long idPedido, String cpf) {
        Pedido pedido = buscarPedidoOuFalhar(idPedido);
        pedido.setStatus(StatusPedido.EM_ENTREGA);
        salvarPedidoComStatusAtualizado(pedido);
        enviarParaFilaEPersistirRegistro(pedido, cpf, StatusPedido.EM_ENTREGA);
        log.info("Pedido atualizado e enviado a fila");
    }

    private Pedido buscarPedidoOuFalhar(Long idPedido) {
        if (!pedidoRepository.existsByPedidoId(idPedido)) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
        return PedidoMapper.INSTANCE.toData(pedidoRepository.findByPedidoId(idPedido));
    }

    private void salvarPedidoComStatusAtualizado(Pedido pedido) {
        PedidoEntity pedidoEntity = PedidoMapper.INSTANCE.toEntity(pedido);
        pedidoRepository.save(pedidoEntity);
    }

    private void enviarParaFilaEPersistirRegistro(Pedido pedido, String cpf, StatusPedido status) {
        deliveryQueueGateway.sendDelivery(pedido);
        criarRegistroEntregaUseCase.criar(cpf, pedido.getPedidoId(), status);
    }
}
