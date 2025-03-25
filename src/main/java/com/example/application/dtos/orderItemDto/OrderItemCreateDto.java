package com.example.application.dtos.orderItemDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemCreateDto {
    @NotNull
    private UUID productId;

    @NotNull
    private int quantity;

    @NotNull
    private UUID orderId;
}
