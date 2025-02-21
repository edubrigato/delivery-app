package com.fiap.delivery.usecase;

import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.exception.PedidoNaoEncontradoException;
import com.fiap.delivery.gateway.db.entity.RegistroEntregaEntity;
import com.fiap.delivery.gateway.db.repository.RegistroEntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CriarRegistroEntregaUseCase {

    private final RegistroEntregaRepository registroEntregaRepository;

    public void criar(String cpf, Long idPedido, StatusPedido status) {
        RegistroEntregaEntity entity = obterOuCriarRegistro(idPedido, status);

        entity.setCpfEntregador(cpf);
        entity.setIdPedido(idPedido);
        entity.setStatus(status);

        registroEntregaRepository.save(entity);
    }

    private RegistroEntregaEntity obterOuCriarRegistro(Long idPedido, StatusPedido status) {
        if (status == StatusPedido.FINALIZADO) {
            return finalizarEntrega(idPedido);
        }
        return criarNovoRegistro();
    }

    private RegistroEntregaEntity finalizarEntrega(Long idPedido) {
        RegistroEntregaEntity entity = registroEntregaRepository.findByIdPedido(idPedido);
        if (entity == null) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado para finalizar entrega.");
        }
        entity.setDataEntrega(LocalDateTime.now());
        return entity;
    }

    private RegistroEntregaEntity criarNovoRegistro() {
        RegistroEntregaEntity entity = new RegistroEntregaEntity();
        entity.setDataInicio(LocalDateTime.now());
        return entity;
    }


}
