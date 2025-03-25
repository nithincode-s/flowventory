package com.example.application.stock;

import com.example.application.dtos.StockMapper;
import com.example.application.dtos.stockDto.StockCreateDto;
import com.example.application.dtos.stockDto.StockReadDto;
import com.example.application.dtos.stockDto.StockUpdateDto;
import com.example.domain.stock.IStockRepo;
import com.example.domain.stock.Stock;
import com.example.domain.supplier.ISupplierRepo;
import com.example.domain.supplier.Supplier;
import com.example.presentation.customException.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StockService implements IStockService{
    @Autowired
    private IStockRepo stockRepo;

    @Autowired
    private ISupplierRepo supplierRepo;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public List<StockReadDto> getAllStocks() {
        List<Stock> stocks = stockRepo.getAllStocks();
        return stocks.stream()
                .map(stockMapper::toStockRead)
                .collect(Collectors.toList());
    }

    @Override
    public StockReadDto createStock(StockCreateDto stockDto) {
        UUID supplierId = stockDto.getSupplierId();
        Supplier supplier = supplierRepo.getSupplierById(supplierId);
        if (supplier == null) {
            throw new ResourceNotFound("Supplier not found with id: " + supplierId);
        }
        Stock stock = stockMapper.toStock(stockDto);
        Stock savedStock = stockRepo.createStock(stock);
        return stockMapper.toStockRead(savedStock);
    }

    @Override
    public StockReadDto getStockById(UUID id) {
        Stock stock = stockRepo.getStockById(id);
        if (stock == null) {
            throw new ResourceNotFound("Stock not found with id: " + id);
        }
        return stockMapper.toStockRead(stock);
    }

    @Override
    public StockReadDto updateStock(UUID id, StockUpdateDto stockDto) {
        Stock existingStock = stockRepo.getStockById(id);
        if (existingStock == null) {
            throw new ResourceNotFound("Stock not found with id: " + id);
        }
        stockMapper.updateStockFromDto(stockDto, existingStock);
        Stock savedStock = stockRepo.updateStock(existingStock);
        return stockMapper.toStockRead(savedStock);
    }

    @Override
    public void deleteStock(UUID id) {
        Stock stock = stockRepo.getStockById(id);
        if (stock == null) {
            throw new ResourceNotFound("Stock not found with id: " + id);
        }
        stockRepo.deleteStock(id);
    }

    @Override
    public List<StockReadDto> getStocksBySupplierId(UUID supplierId) {
        List<Stock> stocks = stockRepo.getStocksBySupplierId(supplierId);
        return stocks.stream()
                .map(stockMapper::toStockRead)
                .collect(Collectors.toList());
    }

    @Override
    public StockReadDto getStocksByProductId(UUID productId) {
        Stock stock = stockRepo.getStocksByProductId(productId);
        if (stock == null) {
            throw new ResourceNotFound("Stock not found with product id: " + productId);
        }
        return stockMapper.toStockRead(stock);
    }

    @Override
    public List<StockReadDto> getLowStockAlerts(int threshold) {
        List<Stock> stocks = stockRepo.getLowStockAlerts(threshold);
        return stocks.stream()
                .map(stockMapper::toStockRead)
                .collect(Collectors.toList());
    }
}
