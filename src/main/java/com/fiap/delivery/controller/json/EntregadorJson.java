package com.fiap.delivery.controller.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class EntregadorJson {

    private UUID id;
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;
    private String placaVeiculo;
    private LocalDate dataNascimento;

}
