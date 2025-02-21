package com.fiap.delivery.usecase;

import com.fiap.delivery.domain.Entregador;
import com.fiap.delivery.gateway.EntregadorGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CriarEntregadorUseCaseUniTest {

    @Mock
    private EntregadorGateway entregadorGateway;

    @InjectMocks
    private CriarEntregadorUseCase criarEntregadorUseCase;

    private Entregador entregador;

    @BeforeEach
    void setUp() {
        entregador = new Entregador();
        entregador.setNome("Entregador");
        entregador.setCpf("123456789");
        entregador.setEndereco("Endereco do Entregador");
        entregador.setTelefone("123456789");
        entregador.setDataNascimento(LocalDate.now());
        entregador.setPlacaVeiculo("Placa Veiculo");
    }


    @Test
    void testCriarEntregador() {
        doNothing().when(entregadorGateway).salvar(entregador);

        criarEntregadorUseCase.criarEntregador(entregador);

        verify(entregadorGateway, times(1)).salvar(entregador);
    }

    @Test
    void testAtualizarEntregador() {
        doNothing().when(entregadorGateway).atualizar(entregador);

        criarEntregadorUseCase.atualizarEntregador(entregador);

        verify(entregadorGateway, times(1)).atualizar(entregador);
    }

}
