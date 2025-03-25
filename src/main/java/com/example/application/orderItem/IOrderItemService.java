package com.example.application.orderItem;

import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.application.dtos.orderItemDto.OrderItemReadDto;
import com.example.application.dtos.orderItemDto.OrderItemUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IOrderItemService {
    public List<OrderItemReadDto> getAllOrderItems();
    public OrderItemReadDto createOrderItem(OrderItemCreateDto orderItem);
    public OrderItemReadDto getOrderItemById(UUID id);
    public OrderItemReadDto updateOrderItem(UUID id, OrderItemUpdateDto orderItem);
    public void deleteOrderItem(UUID id);
}
