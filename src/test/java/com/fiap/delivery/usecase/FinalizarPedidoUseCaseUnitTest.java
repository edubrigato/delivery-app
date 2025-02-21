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
class FinalizarPedidoUseCaseUnitTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private FinalizarPedidoUseCase finalizarPedidoUseCase;

    @Test
    void testFinalizarPedido() {
        Long id = 1L;
        String cpf = "12345";

        doNothing().when(pedidoGateway).finalizarPedido(id, cpf);

        finalizarPedidoUseCase.finalizarPedido(id, cpf);

        verify(pedidoGateway, times(1)).finalizarPedido(id, cpf);
    }

}
