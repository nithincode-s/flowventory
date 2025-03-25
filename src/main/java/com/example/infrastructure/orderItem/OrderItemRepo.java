package com.example.infrastructure.orderItem;

import com.example.domain.orderItem.IOrderItemRepo;
import com.example.domain.orderItem.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderItemRepo implements IOrderItemRepo {
    @Autowired
    private IOrderItemJpaRepo orderItemJpaRepo;

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemJpaRepo.findAll();
    }

    @Override
    public OrderItem getOrderItemById(UUID id) {
        return orderItemJpaRepo.findById(id).orElse(null);
    }

    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemJpaRepo.save(orderItem);
    }

    @Override
    public List<OrderItem> createListOrderItems(List<OrderItem> orderItems) {
        return orderItemJpaRepo.saveAll(orderItems);
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return orderItemJpaRepo.save(orderItem);
    }

    @Override
    public void deleteOrderItem(UUID id) {
        orderItemJpaRepo.deleteById(id);
    }
}
