package com.example.application.dtos;

import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.application.dtos.orderItemDto.OrderItemReadDto;
import com.example.application.dtos.orderItemDto.OrderItemUpdateDto;
import com.example.domain.orderItem.OrderItem;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderId", target = "order.id")
    OrderItem toOrderItem(OrderItemCreateDto incomingOrderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "orderId", target = "order.id")
    void updateOrderItemFromDto(OrderItemUpdateDto updateDto, @MappingTarget OrderItem orderItem);

    @Mapping(source = "order.id", target = "orderId")
    OrderItemReadDto toOrderItemRead(OrderItem orderItem);
}
