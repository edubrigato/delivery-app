package com.fiap.delivery.gateway.db.entity;

import com.fiap.delivery.domain.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private Long pedidoId;
    private Long clienteId;
    private String enderecoEntrega;
    private String cep;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

}
