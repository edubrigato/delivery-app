package com.fiap.delivery.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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
