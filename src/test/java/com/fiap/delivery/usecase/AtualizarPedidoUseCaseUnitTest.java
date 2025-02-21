package com.fiap.delivery.usecase;

import com.fiap.delivery.gateway.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class AtualizarPedidoUseCaseUnitTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AtualizarPedidoUseCase atualizarPedidoUseCase;

    @Test
    void testAtualizarPedido() {
        Long pedidoId = 1L;
        String cpf = "12345678900";

        doNothing().when(pedidoGateway).atualizarPedido(pedidoId, cpf);

        atualizarPedidoUseCase.atualizarPedido(pedidoId, cpf);

        verify(pedidoGateway, times(1)).atualizarPedido(pedidoId, cpf);
    }

}
