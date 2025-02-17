package com.fiap.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Pedido {

    private UUID id;
    private Long pedidoId;
    private Long clienteId;
    private String enderecoEntrega;
    private String cepEntrega;
    private StatusPedido status;

}
