package com.fiap.delivery.controller;

import com.fiap.delivery.config.DeliveryProperties;
import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.gateway.db.entity.PedidoEntity;
import com.fiap.delivery.gateway.db.entity.RegistroEntregaEntity;
import com.fiap.delivery.gateway.db.repository.PedidoRepository;
import com.fiap.delivery.gateway.db.repository.RegistroEntregaRepository;
import com.fiap.delivery.gateway.jpa.PedidoGatewayJpa;
import com.fiap.delivery.gateway.queue.IDeliveryQueueGateway;
import com.fiap.delivery.usecase.AtualizarPedidoUseCase;
import com.fiap.delivery.usecase.CriarEntregadorUseCase;
import com.fiap.delivery.usecase.FinalizarPedidoUseCase;
import com.fiap.delivery.usecase.ObterPedidosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class PedidoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private PedidoRepository pedidoRepository;
    @MockitoBean
    private RegistroEntregaRepository registroEntregaRepository;
    @MockitoBean
    private StreamBridge streamBridge;
    @MockitoBean
    private DeliveryProperties deliveryProperties;
    @InjectMocks
    private PedidoGatewayJpa pedidoGatewayJpa;
    @Mock
    private IDeliveryQueueGateway deliveryQueueGateway;
    @Mock
    private CriarEntregadorUseCase criarEntregadorUseCase;
    @Autowired
    private AtualizarPedidoUseCase atualizarPedidoUseCase;
    @Autowired
    private FinalizarPedidoUseCase finalizarPedidoUseCase;
    @Autowired
    private ObterPedidosUseCase obterPedidosUseCase;

    private PedidoEntity pedido;
    private RegistroEntregaEntity registro;

    @BeforeEach
    public void setUp(){
        pedido = new PedidoEntity();
        pedido.setId(UUID.randomUUID());
        pedido.setPedidoId(1L);
        pedido.setCep("12345678");
        pedido.setEnderecoEntrega("Endereco de Entrega");
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setCpf("12345678");

        registro = new RegistroEntregaEntity();
        registro.setId(UUID.randomUUID());
        registro.setIdPedido(1L);
        registro.setStatus(mock(StatusPedido.class));
        registro.setCpfEntregador("12345678");

        when(pedidoRepository.existsByPedidoId(anyLong())).thenReturn(true);
        when(pedidoRepository.findByPedidoId(anyLong())).thenReturn(pedido);
        when(pedidoRepository.save(any(PedidoEntity.class))).thenReturn(pedido);
        when(registroEntregaRepository.save(any())).thenReturn(registro);
        when(registroEntregaRepository.findByIdPedido(anyLong())).thenReturn(registro);
    }

    @Test
    void testObterPedidosPorCep() throws Exception {
        String cep = "12345678";

        List<PedidoEntity> pedidoList = new ArrayList<>();
        pedidoList.add(pedido);

        when(pedidoRepository.findByCepEntrega(anyString())).thenReturn(pedidoList);

        mockMvc.perform(get("/pedido")
                        .param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].cep").value(cep));
    }

    @Test
    void testFinalizarPedido() throws Exception {
        Long idPedido = 1L;
        String cpfEntregador = "12345678900";

        mockMvc.perform(post("/pedido")
                        .param("idPedido", idPedido.toString())
                        .param("cpfEntregador", cpfEntregador))
                .andExpect(status().isOk());

        verify(pedidoRepository).existsByPedidoId(idPedido);
        verify(pedidoRepository).findByPedidoId(idPedido);
        verify(pedidoRepository).save(any(PedidoEntity.class));
        verify(registroEntregaRepository).save(any());
        verify(registroEntregaRepository).findByIdPedido(idPedido);

    }

    @Test
    void testAtualizarPedido() throws Exception {
        Long idPedido = 1L;
        String cpfEntregador = "12345678900";

        mockMvc.perform(patch("/pedido")
                        .param("idPedido", idPedido.toString())
                        .param("cpfEntregador", cpfEntregador))
                .andExpect(status().isOk());

        verify(pedidoRepository).existsByPedidoId(idPedido);
        verify(pedidoRepository).findByPedidoId(idPedido);
        verify(pedidoRepository).save(any(PedidoEntity.class));
        verify(registroEntregaRepository).save(any());
    }

}
