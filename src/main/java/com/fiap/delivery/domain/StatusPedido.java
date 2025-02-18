package com.fiap.delivery.domain;

public enum StatusPedido {

    PENDENTE(1),
    FINALIZADO(2),
    EM_ENTREGA(3);

    private final int id;

    StatusPedido(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
