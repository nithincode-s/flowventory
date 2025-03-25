package com.example.application.orderItem;

import com.example.application.dtos.OrderItemMapper;
import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.application.dtos.orderItemDto.OrderItemReadDto;
import com.example.application.dtos.orderItemDto.OrderItemUpdateDto;
import com.example.domain.orderItem.IOrderItemRepo;
import com.example.domain.orderItem.OrderItem;
import com.example.presentation.customException.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderItemService implements IOrderItemService {
    @Autowired
    private IOrderItemRepo orderItemRepo;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemReadDto> getAllOrderItems() {
        List<OrderItem> orders = orderItemRepo.getAllOrderItems();
        return orders.stream()
                .map(orderItemMapper::toOrderItemRead)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemReadDto createOrderItem(OrderItemCreateDto orderItemDto) {
        OrderItem order = orderItemMapper.toOrderItem(orderItemDto);
        OrderItem savedOrder = orderItemRepo.createOrderItem(order);
        return orderItemMapper.toOrderItemRead(savedOrder);
    }

    @Override
    public OrderItemReadDto getOrderItemById(UUID id) {
        OrderItem orderItem = orderItemRepo.getOrderItemById(id);
        if (orderItem == null) {
            throw new ResourceNotFound("OrderItem not found with id: " + id);
        }
        return orderItemMapper.toOrderItemRead(orderItem);
    }

    @Override
    public OrderItemReadDto updateOrderItem(UUID id, OrderItemUpdateDto orderItemDto) {
        OrderItem existingOrderItem = orderItemRepo.getOrderItemById(id);
        if (existingOrderItem == null) {
            throw new ResourceNotFound("OrderItem not found with id: " + id);
        }
        orderItemMapper.updateOrderItemFromDto(orderItemDto, existingOrderItem);
        OrderItem savedOrderItem = orderItemRepo.updateOrderItem(existingOrderItem);
        return orderItemMapper.toOrderItemRead(savedOrderItem);
    }

    @Override
    public void deleteOrderItem(UUID id) {
        OrderItem orderItem = orderItemRepo.getOrderItemById(id);
        if (orderItem == null) {
            throw new ResourceNotFound("OrderItem not found with id: " + id);
        }
        orderItemRepo.deleteOrderItem(id);
    }
}
