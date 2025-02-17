package com.fiap.delivery.config.mapper;

import com.fiap.delivery.controller.json.PedidoJson;
import com.fiap.delivery.domain.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    Pedido toData(PedidoEntity pedidoEntity);
    PedidoJson toJson(Pedido pedido);
    PedidoEntity toEntity(Pedido pedido);
}
