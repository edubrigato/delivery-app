package com.fiap.delivery.usecase;

import com.fiap.delivery.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtualizarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    public void atualizarPedido(Long pedidoId, String cpf) {
        pedidoGateway.atualizarPedido(pedidoId, cpf);
    }

}
