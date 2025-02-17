package com.fiap.delivery.controller.json;

import com.fiap.delivery.domain.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PedidoJson {

    private UUID id;
    private Long pedidoId;
    private Long clienteId;
    private String enderecoEntrega;
    private String cepEntrega;
    private StatusPedido status;

}
