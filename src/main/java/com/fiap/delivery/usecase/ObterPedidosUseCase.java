package com.fiap.delivery.usecase;

import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.gateway.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterPedidosUseCase {

    private final PedidoGateway pedidoGateway;

    @Transactional
    public List<Pedido> obter(String cep){
        return pedidoGateway.obterPedidosPorCep(cep);
    }

}
