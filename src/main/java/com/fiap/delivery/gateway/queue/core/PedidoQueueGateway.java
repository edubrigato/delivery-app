package com.fiap.delivery.gateway.queue.core;

import com.fiap.delivery.domain.Pedido;
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
        try {
            log.info("Pedido recebido");
            pedidoGateway.salvar(pedido);
            log.info("Pedido salvo");
        } catch (RuntimeException e){
            log.error("Erro ao salvar no banco de dados", e);
        }
    }
}
