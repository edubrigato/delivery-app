package com.fiap.delivery.usecase;

import com.fiap.delivery.domain.StatusPedido;
import com.fiap.delivery.exception.PedidoNaoEncontradoException;
import com.fiap.delivery.gateway.db.entity.RegistroEntregaEntity;
import com.fiap.delivery.gateway.db.repository.RegistroEntregaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarRegistroEntregaUseCaseUnitTest {

    private static final String CPF = "123456789";
    private static final Long ID_PEDIDO = 1L;

    @Mock
    private RegistroEntregaRepository registroEntregaRepository;

    @InjectMocks
    private CriarRegistroEntregaUseCase criarRegistroEntregaUseCase;


    @Test
    void testCriarRegistroEntregaUseCaseFinalizado() {
        StatusPedido status = StatusPedido.FINALIZADO;

        when(registroEntregaRepository.findByIdPedido(anyLong())).thenReturn(new RegistroEntregaEntity());
        when(registroEntregaRepository.save(any())).thenReturn(new RegistroEntregaEntity());

        criarRegistroEntregaUseCase.criar(CPF, ID_PEDIDO, status);

        verify(registroEntregaRepository, times(1)).save(any());
    }

    @Test
    void testCriarRegistroEntregaUseCaseEmEntrega() {
        StatusPedido status = StatusPedido.EM_ENTREGA;

        when(registroEntregaRepository.save(any())).thenReturn(new RegistroEntregaEntity());

        criarRegistroEntregaUseCase.criar(CPF, ID_PEDIDO, status);

        verify(registroEntregaRepository, times(1)).save(any());
    }

    @Test
    void testExceptionNaoEncontrado() {

        when(registroEntregaRepository.findByIdPedido(anyLong())).thenReturn(null);

        PedidoNaoEncontradoException thrown = assertThrows(PedidoNaoEncontradoException.class, () -> {
            criarRegistroEntregaUseCase.criar(CPF, ID_PEDIDO, StatusPedido.FINALIZADO);
        });

        assertEquals("Pedido não encontrado para finalizar entrega.", thrown.getMessage());
    }


}
