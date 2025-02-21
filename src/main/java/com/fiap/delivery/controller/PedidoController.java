package com.fiap.delivery.controller;

import com.fiap.delivery.config.mapper.PedidoMapper;
import com.fiap.delivery.controller.json.PedidoJson;
import com.fiap.delivery.usecase.AtualizarPedidoUseCase;
import com.fiap.delivery.usecase.FinalizarPedidoUseCase;
import com.fiap.delivery.usecase.ObterPedidosUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final ObterPedidosUseCase obterPedidosUseCase;
    private final FinalizarPedidoUseCase finalizarPedidoUseCase;
    private final AtualizarPedidoUseCase atualizarPedidoUseCase;

    @GetMapping()
    public ResponseEntity<List<PedidoJson>> obterPedidosPorCep(@RequestParam String cep) {
        return new ResponseEntity<>(obterPedidosUseCase.obter(cep).stream().map(PedidoMapper.INSTANCE::toJson).toList(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> finalizarPedido(@RequestParam Long idPedido,
                                @RequestParam String cpfEntregador) {
        finalizarPedidoUseCase.finalizarPedido(idPedido, cpfEntregador);
        log.info("Pedido {} atualizado com sucesso", idPedido);
        return new ResponseEntity<>("Pedido finalizado com sucesso", HttpStatus.OK);
    }


    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> atualizarPedido(@RequestParam Long idPedido,
                                @RequestParam String cpfEntregador) {
        atualizarPedidoUseCase.atualizarPedido(idPedido, cpfEntregador);
        log.info("Pedido {} atualizado com sucesso", idPedido);
        return new ResponseEntity<>("Entrega iniciada", HttpStatus.OK);
    }
}
