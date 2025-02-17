package com.fiap.delivery.controller;

import com.fiap.delivery.config.mapper.EntregadorMapper;
import com.fiap.delivery.controller.json.EntregadorJson;
import com.fiap.delivery.usecase.CriarEntregadorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregador")
@RequiredArgsConstructor
@Slf4j
public class EntregadorController {

    private final CriarEntregadorUseCase criarEntregadorUseCase;

    @PostMapping
    public ResponseEntity<Void> criarEntregador(@RequestBody EntregadorJson entregador) {
        criarEntregadorUseCase.criarEntregador(EntregadorMapper.INSTANCE.toData(entregador));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping
    private ResponseEntity<Void> updateEntregador(@RequestBody EntregadorJson entregador) {
        criarEntregadorUseCase.atualizarEntregador(EntregadorMapper.INSTANCE.toData(entregador));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
