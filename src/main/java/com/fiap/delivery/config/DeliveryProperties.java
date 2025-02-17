package com.fiap.delivery.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class DeliveryProperties {

    private String deliverySendChannel = "orderSupplier-out-0";

}
