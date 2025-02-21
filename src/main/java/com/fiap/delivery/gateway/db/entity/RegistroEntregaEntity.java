package com.fiap.delivery.gateway.db.entity;

import com.fiap.delivery.domain.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "registro")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RegistroEntregaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataEntrega;
    private String cpfEntregador;
    private Long idPedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

}
