package com.fiap.delivery.gateway;

import com.fiap.delivery.domain.Entregador;

public interface EntregadorGateway {

    void salvar(Entregador entregador);

    void atualizar(Entregador entregador);
}
