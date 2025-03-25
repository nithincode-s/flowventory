package com.example.presentation;

import com.example.application.dtos.stockDto.StockCreateDto;
import com.example.application.dtos.stockDto.StockReadDto;
import com.example.application.dtos.stockDto.StockUpdateDto;
import com.example.application.stock.IStockService;
import com.example.domain.stock.Stock;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stocks")
public class StockController {
    @Autowired
    private IStockService stockService;

    @GetMapping
    public ResponseEntity<List<StockReadDto>> getAllStocks() {
        List<StockReadDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    @PostMapping
    public ResponseEntity<StockReadDto> createStock(@RequestBody @Valid StockCreateDto stock) {
        StockReadDto createdStock = stockService.createStock(stock);
        // created
        return ResponseEntity.ok(createdStock);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockReadDto> getStockById(@PathVariable UUID id) {
        StockReadDto stock = stockService.getStockById(id);
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockReadDto> updateStock(@PathVariable UUID id, @RequestBody @Valid StockUpdateDto stock) {
        StockReadDto updatedStock = stockService.updateStock(id, stock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable UUID id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<StockReadDto>> getStocksBySupplierId(@PathVariable UUID supplierId) {
        List<StockReadDto> stocks = stockService.getStocksBySupplierId(supplierId);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<StockReadDto> getStocksByProductId(@PathVariable UUID productId) {
        StockReadDto stock = stockService.getStocksByProductId(productId);
        return ResponseEntity.ok(stock);
    }

    @GetMapping("/lowstock")
    public ResponseEntity<List<StockReadDto>> getLowStockAlerts(@RequestParam(defaultValue = "10") int threshold) {
        List<StockReadDto> lowStockItems = stockService.getLowStockAlerts(threshold);
        return ResponseEntity.ok(lowStockItems);
    }
}
