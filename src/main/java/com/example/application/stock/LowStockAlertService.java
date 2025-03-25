package com.example.application.stock;

import com.example.domain.stock.Stock;
import com.example.application.notification.NotificationService;
import com.example.domain.stock.IStockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LowStockAlertService {

    @Autowired
    private IStockRepo stockRepository;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(fixedRate = 60000)
    public void checkLowStock() {
        List<Stock> lowStockItems = stockRepository.getLowStockAlerts(10);
        if (!lowStockItems.isEmpty()) {
            notificationService.sendLowStockAlert(lowStockItems);
        }
    }
}
