package com.fiap.delivery.gateway.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "entregador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntregadorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String placaVeiculo;
    private LocalDate dataNascimento;

}
