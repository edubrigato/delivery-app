package com.fiap.delivery.gateway;

import com.fiap.delivery.domain.Pedido;

import java.util.List;

public interface PedidoGateway {

    List<Pedido> obterPedidosPorCep(String cep);

    void salvar(Pedido pedido);

    void finalizarPedido(Long idPedido,String cpf);

    void atualizarPedido(Long idPedido,String cpf);
}
