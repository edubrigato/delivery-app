package com.fiap.delivery.queue;

import com.fiap.delivery.domain.Pedido;
import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.gateway.PedidoGateway;
import com.fiap.delivery.gateway.queue.core.PedidoQueueGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PedidoQueueGatewayUnitTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private PedidoQueueGateway pedidoQueueGateway;

    @Mock
    private Logger log;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setEnderecoEntrega("12345678, Rua Fictícia, 123");
        pedido.setStatus(StatusPedido.PENDENTE);
    }

    @Test
    void testAccept_Success() {
        doNothing().when(pedidoGateway).salvar(any(Pedido.class));

        pedidoQueueGateway.accept(pedido);

        verify(pedidoGateway).salvar(pedido);
        assertEquals("12345678", pedido.getCep());
        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());
    }

}
