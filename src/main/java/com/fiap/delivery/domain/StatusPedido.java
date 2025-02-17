package com.fiap.delivery.domain;

public enum StatusPedido {

    PENDENTE(1),
    ENTREGUE(2);

    private final int id;

    StatusPedido(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
