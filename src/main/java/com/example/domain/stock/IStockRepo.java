package com.example.domain.stock;

import java.util.List;
import java.util.UUID;

public interface IStockRepo {
    public List<Stock> getAllStocks();
    public Stock createStock(Stock stock);
    public Stock getStockById(UUID id);
    public Stock updateStock(Stock stock);
    public void deleteStock(UUID id);
    public List<Stock> getStocksBySupplierId(UUID supplierId);
    public Stock getStocksByProductId(UUID productId);
    public List<Stock> getLowStockAlerts(int threshold);
}
