package com.fiap.delivery.controller;

import com.fiap.delivery.gateway.db.entity.EntregadorEntity;
import com.fiap.delivery.gateway.db.repository.EntregadorRepository;
import com.fiap.delivery.gateway.jpa.EntregadorGatewayJpa;
import com.fiap.delivery.usecase.CriarEntregadorUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class EntregadorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EntregadorRepository entregadorRepository;

    @InjectMocks
    private EntregadorGatewayJpa entregadorGatewayJpa;

    @Autowired
    private CriarEntregadorUseCase criarEntregadorUseCase;

    @Test
    void testUpdateEntregador() throws Exception {
        EntregadorEntity mockEntregador = new EntregadorEntity();
        mockEntregador.setId(UUID.randomUUID());
        mockEntregador.setNome("João Atualizado");
        mockEntregador.setCpf("12345678900");

        when(entregadorRepository.findByCpf(anyString())).thenReturn(mockEntregador);

        mockMvc.perform(MockMvcRequestBuilders.patch("/entregador")
                        .contentType("application/json")
                        .content("{ \"nome\": \"João Atualizado\", \"cpf\": \"12345678900\" }"))
                .andExpect(status().isOk())
                .andReturn();

        verify(entregadorRepository, times(1)).findByCpf("12345678900");
        verify(entregadorRepository, times(1)).saveAndFlush(any(EntregadorEntity.class));
    }


    @Test
    void testCriarEntregador() throws Exception {
        String entregadorJson = "{"
                + "\"nome\": \"João\","
                + "\"cpf\": \"12345678901\","
                + "\"telefone\": \"1234567890\""
                + "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/entregador")
                        .contentType("application/json")
                        .content(entregadorJson))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
