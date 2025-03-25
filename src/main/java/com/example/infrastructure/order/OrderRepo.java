package com.example.infrastructure.order;

import com.example.domain.order.IOrderRepo;
import com.example.domain.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepo implements IOrderRepo {
    private static final Logger logger = LoggerFactory.getLogger(OrderRepo.class);
    @Autowired
    private IOrderJpaRepo orderJpaRepo;

    @Override
    public List<Order> getAllOrders() {
        logger.info("Fetching all orders from the database");
        return orderJpaRepo.findAll();
    }

    @Override
    public Order getOrderById(UUID id) {
        logger.info("Fetching order with ID: {}", id);
        return orderJpaRepo.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Order order) {
        logger.info("Creating a new order: {}", order);
        return orderJpaRepo.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        logger.info("Updating order with ID: {}", order.getId());
        return orderJpaRepo.save(order);
    }

    @Override
    public void deleteOrder(UUID id) {
        logger.info("Deleting order with ID: {} from the database", id);
        orderJpaRepo.deleteById(id);
    }
}
