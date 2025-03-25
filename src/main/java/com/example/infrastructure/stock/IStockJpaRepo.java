package com.example.infrastructure.stock;

import com.example.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IStockJpaRepo extends JpaRepository<Stock, UUID> {
    public List<Stock> getStocksBySupplierId(UUID supplierId);
    public Stock getStocksByProductId(UUID productId);

    @Query("SELECT s FROM Stock s WHERE s.quantity < :threshold")
    public List<Stock> getLowStockAlerts(int threshold);
}
