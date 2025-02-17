package com.fiap.delivery.config.mapper;

import com.fiap.delivery.controller.json.EntregadorJson;
import com.fiap.delivery.domain.Entregador;
import com.fiap.delivery.gateway.db.entity.EntregadorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntregadorMapper {

    EntregadorMapper INSTANCE = Mappers.getMapper(EntregadorMapper.class);

    EntregadorEntity toEntity(Entregador entregador);

    Entregador toData(EntregadorJson entregadorJson);

}
