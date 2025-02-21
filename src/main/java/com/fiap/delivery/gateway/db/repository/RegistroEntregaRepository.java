package com.fiap.delivery.gateway.db.repository;

import com.fiap.delivery.gateway.db.entity.RegistroEntregaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RegistroEntregaRepository extends JpaRepository<RegistroEntregaEntity, UUID> {

    RegistroEntregaEntity findByIdPedido(Long idPedido);
    
}
