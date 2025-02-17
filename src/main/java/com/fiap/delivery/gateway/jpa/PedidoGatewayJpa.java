package com.fiap.delivery.gateway.jpa;

import com.fiap.delivery.config.mapper.PedidoMapper;
import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.exception.PedidoNaoEncontradoException;
import com.fiap.delivery.gateway.PedidoGateway;
import com.fiap.delivery.gateway.db.repository.PedidoRepository;
import com.fiap.delivery.gateway.queue.IDeliveryQueueGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PedidoGatewayJpa implements PedidoGateway {

    private final PedidoRepository pedidoRepository;
    private final IDeliveryQueueGateway deliveryQueueGateway;

    @Override
    public List<Pedido> obterPedidosPorCep(String cep) {
        return pedidoRepository.findByCepEntrega(cep).stream().map(PedidoMapper.INSTANCE::toData).toList();
    }

    @Override
    public void salvar(Pedido pedido) {
        pedidoRepository.saveAndFlush(PedidoMapper.INSTANCE.toEntity(pedido));
    }

    @Override
    public void finalizarPedido(Long idPedido) {
        if (!pedidoRepository.existsById(idPedido)) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
        Pedido pedido = PedidoMapper.INSTANCE.toData(pedidoRepository.findByPedidoId(idPedido));
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedidoRepository.save(PedidoMapper.INSTANCE.toEntity(pedido));
        deliveryQueueGateway.sendDelivery(pedido);
        log.info("Pedido finalizado e enviado a fila");
    }
}
