package com.example.application.dtos.stockDto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StockUpdateDto {
    private UUID productId;
    private int quantity;
    private UUID supplierId;
}
