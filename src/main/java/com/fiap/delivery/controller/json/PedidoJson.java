package com.fiap.delivery.controller.json;

import com.fiap.delivery.domain.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoJson {

    private UUID id;
    private Long pedidoId;
    private Long clienteId;
    private String enderecoEntrega;
    private StatusPedido status;
    private String cep;

}
