package com.fiap.delivery.gateway.queue.core;

import com.fiap.delivery.config.DeliveryProperties;
import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.gateway.queue.IDeliveryQueueGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeliveryQueueGateway implements IDeliveryQueueGateway {

    private final StreamBridge streamBridge;
    private final DeliveryProperties deliveryProperties;

    @Override
    public void sendDelivery(Pedido pedido) {
        log.info("Send delivery");
        streamBridge.send(deliveryProperties.getDeliverySendChannel(), pedido);
    }
}
