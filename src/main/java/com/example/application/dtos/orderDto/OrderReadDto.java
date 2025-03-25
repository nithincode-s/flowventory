package com.example.application.dtos.orderDto;

import com.example.domain.order.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class OrderReadDto {
    private UUID id;
    private Date orderDate;
    private OrderStatus status;
    private UUID supplierId;
}
