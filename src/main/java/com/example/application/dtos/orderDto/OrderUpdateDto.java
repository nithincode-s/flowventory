package com.example.application.dtos.orderDto;

import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.domain.order.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderUpdateDto {
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private UUID supplierId;
    private List<OrderItemCreateDto> orderItems;
}
