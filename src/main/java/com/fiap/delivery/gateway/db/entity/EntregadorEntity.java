package com.fiap.delivery.gateway.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "entregador")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EntregadorEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String placaVeiculo;
    private LocalDate dataNascimento;

}
