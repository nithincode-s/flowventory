package com.example.application.dtos.orderItemDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemUpdateDto {
    private UUID productId;
    private int quantity;
    private UUID orderId;
}
