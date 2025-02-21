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
    public ResponseEntity<String> criarEntregador(@RequestBody EntregadorJson entregador) {
        criarEntregadorUseCase.criarEntregador(EntregadorMapper.INSTANCE.toData(entregador));
        log.info("Entregador criado com sucesso");
        return new ResponseEntity<>("Entregador criado com sucesso", HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<String> updateEntregador(@RequestBody EntregadorJson entregador) {
        criarEntregadorUseCase.atualizarEntregador(EntregadorMapper.INSTANCE.toData(entregador));
        log.info("Entregador atualizado com sucesso");
        return new ResponseEntity<>("Entregador atualizado com sucesso", HttpStatus.OK);
    }
}
