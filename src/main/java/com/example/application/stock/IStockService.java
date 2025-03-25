package com.example.application.stock;

import com.example.application.dtos.stockDto.StockCreateDto;
import com.example.application.dtos.stockDto.StockReadDto;
import com.example.application.dtos.stockDto.StockUpdateDto;

import java.util.List;
import java.util.UUID;

public interface IStockService {
    public List<StockReadDto> getAllStocks();
    public StockReadDto createStock(StockCreateDto stock);
    public StockReadDto getStockById(UUID id);
    public StockReadDto updateStock(UUID id, StockUpdateDto stock);
    public void deleteStock(UUID id);
    public List<StockReadDto> getStocksBySupplierId(UUID supplierId);
    public StockReadDto getStocksByProductId(UUID productId);
    public List<StockReadDto> getLowStockAlerts(int threshold);
}
