package com.fiap.delivery.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Entregador {

    private UUID id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String placaVeiculo;
    private LocalDate dataNascimento;

}
