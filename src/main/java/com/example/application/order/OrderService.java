package com.example.application.order;

import com.example.application.dtos.OrderItemMapper;
import com.example.application.dtos.OrderMapper;
import com.example.application.dtos.orderDto.OrderCreateDto;
import com.example.application.dtos.orderDto.OrderReadDto;
import com.example.application.dtos.orderDto.OrderUpdateDto;
import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.application.notification.NotificationService;
import com.example.domain.order.IOrderRepo;
import com.example.domain.order.Order;
import com.example.domain.order.OrderStatus;
import com.example.domain.orderItem.IOrderItemRepo;
import com.example.domain.orderItem.OrderItem;
import com.example.domain.stock.IStockRepo;
import com.example.domain.stock.Stock;
import com.example.presentation.customException.OutOfStock;
import com.example.presentation.customException.ResourceNotFound;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
    private IOrderItemRepo orderItemRepo;
    @Autowired
    private IOrderRepo orderRepo;

    @Autowired
    private IStockRepo stockRepo;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private NotificationService notificationService;

    @Override
    public List<OrderReadDto> getAllOrders() {
        logger.info("Retrieving all orders");
        List<Order> orders = orderRepo.getAllOrders();
        return orders.stream()
                .map(order -> {
                    OrderReadDto orderReadDto = orderMapper.toOrderRead(order);
                    return orderReadDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderReadDto createOrder(OrderCreateDto orderDto) {
        logger.info("Creating order with details: {}", orderDto);
        for (OrderItemCreateDto item : orderDto.getOrderItems()) {
            Stock stock = stockRepo.getStocksByProductId(item.getProductId());
            if (stock == null) {
                logger.error("Product not found with id: {}", item.getProductId());
                throw new ResourceNotFound("Product not found with id: " + item.getProductId());
            }
            if (stock.getQuantity() < item.getQuantity()) {
                logger.error("Insufficient stock for product id: {}", item.getProductId());
                throw new OutOfStock("Insufficient stock for product id: " + item.getProductId());
            }
            stock.setQuantity(stock.getQuantity() - item.getQuantity());
            stockRepo.updateStock(stock);
        }

        Order order = orderMapper.toOrder(orderDto);

        if (order.getOrderDate() == null || order.getStatus() == null || order.getSupplier() == null) {
            logger.error("Order date, status, and supplier ID must not be null");
            throw new IllegalArgumentException("Order date, status, and supplier ID must not be null");
        }

        Order savedOrder = orderRepo.createOrder(order);
        logger.info("Order created successfully: {}", savedOrder);
        notificationService.sendOrderShippedNotification(savedOrder);

        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                .map(itemDto -> {
                    OrderItem orderItem = orderItemMapper.toOrderItem(itemDto);
                    orderItem.setOrder(savedOrder);
                    return orderItem;
                })
                .collect(Collectors.toList());

        orderItemRepo.createListOrderItems(orderItems);
        logger.info("Order items created successfully for order ID: {}", savedOrder.getId());

        return orderMapper.toOrderRead(savedOrder);
    }

    @Override
    public OrderReadDto getOrderById(UUID id) {
        logger.info("Retrieving order with ID: {}", id);
        Order order = orderRepo.getOrderById(id);
        if (order == null) {
            logger.error("Order not found with ID: {}", id);
            throw new ResourceNotFound("Order not found with id: " + id);
        }
        return orderMapper.toOrderRead(order);
    }

    @Override
    public OrderReadDto updateOrder(UUID id, OrderUpdateDto orderDto) {
        logger.info("Updating order with ID: {}", id);
        Order existingOrder = orderRepo.getOrderById(id);
        if (existingOrder == null) {
            logger.error("Order not found with ID: {}", id);
            throw new ResourceNotFound("Order not found with id: " + id);
        }
        orderMapper.updateOrderFromDto(orderDto, existingOrder);
        Order savedOrder = orderRepo.updateOrder(existingOrder);
        logger.info("Order updated successfully: {}", savedOrder);

        if (savedOrder.getStatus() == OrderStatus.SHIPPED) {
            notificationService.sendOrderShippedNotification(savedOrder);
        }

        return orderMapper.toOrderRead(savedOrder);
    }

    @Override
    public void deleteOrder(UUID id) {
        logger.info("Deleting order with ID: {}", id);
        Order order = orderRepo.getOrderById(id);
        if (order == null) {
            logger.error("Order not found with ID: {}", id);
            throw new ResourceNotFound("Order not found with id: " + id);
        }
        orderRepo.deleteOrder(id);
        logger.info("Order deleted successfully with ID: {}", id);
    }
}
