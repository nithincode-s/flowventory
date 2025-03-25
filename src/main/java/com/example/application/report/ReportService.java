package com.example.application.report;

import com.example.domain.order.Order;
import com.example.domain.stock.Stock;
import com.example.domain.order.IOrderRepo;
import com.example.domain.stock.IStockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private IOrderRepo orderRepository;

    @Autowired
    private IStockRepo stockRepository;

    public InventoryReport generateInventoryReport() {
        List<Stock> stockList = stockRepository.getAllStocks();
        InventoryReport report = new InventoryReport();
        report.setStockList(stockList);
        report.setReportDate(LocalDate.now().toString());
        return report;
    }

    public SalesReport generateSalesReport() {
        List<Order> orderList = orderRepository.getAllOrders();
        SalesReport report = new SalesReport();
        report.setOrderList(orderList);
        report.setReportDate(LocalDate.now().toString());
        return report;
    }

    public void saveInventoryReportToFile(InventoryReport report) {
        String fileName = "inventory-report-" + report.getReportDate() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Inventory Report Date: " + report.getReportDate() + "\n");
            writer.write("Stock List:\n");
            for (Stock stock : report.getStockList()) {
                writer.write("Product ID: " + stock.getProductId() + ", Quantity: " + stock.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSalesReportToFile(SalesReport report) {
        String fileName = "sales-report-" + report.getReportDate() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Sales Report Date: " + report.getReportDate() + "\n");
            writer.write("Order List:\n");
            for (Order order : report.getOrderList()) {
                writer.write("Order ID: " + order.getId() + ", Status: " + order.getStatus() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
