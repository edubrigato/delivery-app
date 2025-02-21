package com.fiap.delivery.gateway.db.entity;

import com.fiap.delivery.domain.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private Long pedidoId;
    private String cpf;
    private String enderecoEntrega;
    private String cep;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

}
