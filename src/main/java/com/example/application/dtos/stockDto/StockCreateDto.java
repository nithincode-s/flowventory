package com.example.application.dtos.stockDto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StockCreateDto {
    @NotNull
    private UUID productId;

    @NotNull
    private int quantity;

    @NotNull
    private UUID supplierId;
}
