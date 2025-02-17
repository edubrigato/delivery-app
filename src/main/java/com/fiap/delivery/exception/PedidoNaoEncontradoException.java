package com.fiap.delivery.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }

}
