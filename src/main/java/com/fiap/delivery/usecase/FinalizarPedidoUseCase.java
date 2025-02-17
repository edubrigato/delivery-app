package com.fiap.delivery.usecase;

import com.fiap.delivery.gateway.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinalizarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Transactional
    public void finalizarPedido(Long idPedido) {
        pedidoGateway.finalizarPedido(idPedido);
    }
}
