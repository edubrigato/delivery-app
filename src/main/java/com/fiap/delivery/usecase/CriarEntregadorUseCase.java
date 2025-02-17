package com.fiap.delivery.usecase;

import com.fiap.delivery.domain.Entregador;
import com.fiap.delivery.gateway.EntregadorGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarEntregadorUseCase {

    private final EntregadorGateway entregadorGateway;


    public void criarEntregador(Entregador entregador) {
        entregadorGateway.salvar(entregador);
    }

    public void atualizarEntregador(Entregador entregador) {
        entregadorGateway.atualizar(entregador);
    }

}


