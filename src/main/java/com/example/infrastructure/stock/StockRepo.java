package com.example.infrastructure.stock;

import com.example.domain.stock.IStockRepo;
import com.example.domain.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StockRepo implements IStockRepo {
    @Autowired
    private IStockJpaRepo stockJpaRepo;

    @Override
    public List<Stock> getAllStocks() {
        return stockJpaRepo.findAll();
    }

    @Override
    public Stock getStockById(UUID id) {
        return stockJpaRepo.findById(id).orElse(null);
    }

    @Override
    public Stock createStock(Stock stock) {
        return stockJpaRepo.save(stock);
    }

    @Override
    public Stock updateStock(Stock stock) {
        return stockJpaRepo.save(stock);
    }

    @Override
    public void deleteStock(UUID id) {
        stockJpaRepo.deleteById(id);
    }

    @Override
    public List<Stock> getStocksBySupplierId(UUID supplierId) {
        return stockJpaRepo.getStocksBySupplierId(supplierId);
    }

    @Override
    public Stock getStocksByProductId(UUID productId) {
        return stockJpaRepo.getStocksByProductId(productId);
    }

    @Override
    public List<Stock> getLowStockAlerts(int threshold) {
        return stockJpaRepo.getLowStockAlerts(threshold);
    }
}
