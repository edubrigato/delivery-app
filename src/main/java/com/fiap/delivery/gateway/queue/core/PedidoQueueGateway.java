package com.fiap.delivery.gateway.queue.core;

import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.gateway.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class PedidoQueueGateway implements Consumer<Pedido> {

    private final PedidoGateway pedidoGateway;

    @Override
    public void accept(Pedido pedido) {
        log.info("Pedido recebido");
        int index = pedido.getEnderecoEntrega().indexOf(",");
        pedido.setCep(pedido.getEnderecoEntrega().substring(0, index).trim());
        pedido.setEnderecoEntrega(pedido.getEnderecoEntrega().substring(index + 1).trim());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedidoGateway.salvar(pedido);
        log.info("Pedido salvo");
    }
}
