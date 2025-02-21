package com.fiap.delivery.usecase;

import com.fiap.delivery.gateway.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ObterPedidosUseCaseUnitTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ObterPedidosUseCase obterPedidosUseCase;

    @Test
    void testObterPedidosUseCase() {
        String cep = "123";

        when(pedidoGateway.obterPedidosPorCep(anyString())).thenReturn(new ArrayList<>());

        obterPedidosUseCase.obter(cep);

        verify(pedidoGateway, times(1)).obterPedidosPorCep(cep);
    }

}
