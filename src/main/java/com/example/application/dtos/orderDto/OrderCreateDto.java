package com.example.application.dtos.orderDto;

import com.example.application.dtos.orderItemDto.OrderItemCreateDto;
import com.example.domain.order.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderCreateDto {
    @NotNull
    private Date orderDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PROCESSING;

    @NotNull
    private UUID supplierId ;

    @NotNull
    private List<OrderItemCreateDto> orderItems;
}
