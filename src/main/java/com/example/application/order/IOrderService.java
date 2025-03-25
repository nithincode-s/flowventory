package com.example.application.order;

import com.example.application.dtos.orderDto.OrderCreateDto;
import com.example.application.dtos.orderDto.OrderReadDto;
import com.example.application.dtos.orderDto.OrderUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    public List<OrderReadDto> getAllOrders();
    public OrderReadDto createOrder(OrderCreateDto order);
    public OrderReadDto getOrderById(UUID id);
    public OrderReadDto updateOrder(UUID id, OrderUpdateDto order);
    public void deleteOrder(UUID id);
}
