package com.example.domain.order;

import com.example.application.dtos.orderDto.OrderCreateDto;
import com.example.application.dtos.orderDto.OrderReadDto;
import com.example.application.dtos.orderDto.OrderUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IOrderRepo {
    public List<Order> getAllOrders();
    public Order createOrder(Order order);
    public Order getOrderById(UUID id);
    public Order updateOrder(Order order);
    public void deleteOrder(UUID id);
}
