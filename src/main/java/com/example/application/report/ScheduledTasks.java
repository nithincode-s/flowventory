package com.example.application.report;

import com.example.application.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private ReportService reportService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void generateDailyReport() {
        try {
            logger.info("Starting daily report generation.");
            InventoryReport inventoryReport = reportService.generateInventoryReport();
            SalesReport salesReport = reportService.generateSalesReport();

            reportService.saveInventoryReportToFile(inventoryReport);
            reportService.saveSalesReportToFile(salesReport);

            logger.info("Daily reports generated and saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
