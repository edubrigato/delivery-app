package com.fiap.delivery.gateway.jpa;

import com.fiap.delivery.config.mapper.EntregadorMapper;
import com.fiap.delivery.domain.Entregador;
import com.fiap.delivery.gateway.db.entity.EntregadorEntity;
import com.fiap.delivery.gateway.db.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntregadorGatewayJpa implements com.fiap.delivery.gateway.EntregadorGateway {

    private final EntregadorRepository entregadorRepository;

    @Override
    public void salvar(Entregador entregador) {
       entregadorRepository.saveAndFlush(EntregadorMapper.INSTANCE.toEntity(entregador));
    }

    @Override
    public void atualizar(Entregador entregador) {
        EntregadorEntity entregadorEntity = entregadorRepository.findByCpf(entregador.getCpf());
        entregador.setId(entregadorEntity.getId());
       entregadorRepository.saveAndFlush(EntregadorMapper.INSTANCE.toEntity(entregador));
    }
}
