package com.fiap.delivery.usecase;

import com.fiap.delivery.gateway.db.entity.RegistroEntregaEntity;
import com.fiap.delivery.gateway.db.repository.RegistroEntregaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarRegistroEntregaUseCase {

    private final RegistroEntregaRepository registroEntregaRepository;

    public void criar(String cpf, Long idPedido){
        RegistroEntregaEntity entity = new RegistroEntregaEntity();
        entity.setCpf(cpf);
        entity.setIdPedido(idPedido);
        registroEntregaRepository.save(entity);
    }

}
