package com.fiap.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {

    private UUID id;
    private Long pedidoId;
    private String cpf;
    private String enderecoEntrega;
    private String cep;
    private StatusPedido status;

}
