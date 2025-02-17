package com.fiap.delivery.gateway.queue;

import com.fiap.delivery.domain.Pedido;

public interface IDeliveryQueueGateway {

    void sendDelivery(Pedido pedido);

}
