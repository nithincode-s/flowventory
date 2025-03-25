package com.example.domain.orderItem;

import java.util.List;
import java.util.UUID;

public interface IOrderItemRepo {
    public List<OrderItem> getAllOrderItems();
    public OrderItem createOrderItem(OrderItem orderItem);
    public List<OrderItem> createListOrderItems(List<OrderItem> orderItems);
    public OrderItem getOrderItemById(UUID id);
    public OrderItem updateOrderItem(OrderItem orderItem);
    public void deleteOrderItem(UUID id);
}
