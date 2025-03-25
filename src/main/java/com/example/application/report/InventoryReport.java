package com.example.application.report;

import com.example.domain.stock.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InventoryReport {
    private List<Stock> stockList;
    private String reportDate;
}