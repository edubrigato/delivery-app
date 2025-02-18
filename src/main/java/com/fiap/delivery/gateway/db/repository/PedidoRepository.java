package com.fiap.delivery.gateway.db.repository;

import com.fiap.delivery.gateway.db.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

    @Query("SELECT p FROM PedidoEntity p WHERE p.cep LIKE :cep% AND p.status = 'PENDENTE'")
    List<PedidoEntity> findByCepEntrega(@Param("cep") String cep);

    PedidoEntity findByPedidoId(Long pedidoId);

    @Query("SELECT COUNT(p) > 0 FROM PedidoEntity p WHERE p.pedidoId = :pedidoId")
    boolean existsByPedidoId(@Param("pedidoId") Long pedidoId);

}
