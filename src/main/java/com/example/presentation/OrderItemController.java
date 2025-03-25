package com.example.presentation;

import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.application.dtos.orderItemDto.OrderItemReadDto;
import com.example.application.dtos.orderItemDto.OrderItemUpdateDto;
import com.example.application.orderItem.IOrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orderitems")
public class OrderItemController {
    @Autowired
    private IOrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemReadDto>> getAllOrderItems() {
        List<OrderItemReadDto> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping
    public ResponseEntity<OrderItemReadDto> createOrderItem(@RequestBody @Valid OrderItemCreateDto orderItem) {
        OrderItemReadDto createdOrderItem = orderItemService.createOrderItem(orderItem);
        // created
        return ResponseEntity.ok(createdOrderItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemReadDto> getOrderItemById(@PathVariable UUID id) {
        OrderItemReadDto orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemReadDto> updateOrder(@PathVariable UUID id, @RequestBody @Valid OrderItemUpdateDto orderItem) {
        OrderItemReadDto updatedOrderItem = orderItemService.updateOrderItem(id, orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
