package com.example.application.notification;

import com.example.domain.order.Order;
import com.example.domain.stock.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;

    private String myEmail = "duykhang3902@gmail.com";

    public void sendOrderShippedNotification(Order order) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(myEmail);
        message.setSubject("Your Order Has Shipped!");
        message.setText("Dear Customer,\n\nYour order with ID " + order.getId() + " has been shipped.\n\nThank you for shopping with us!");

        mailSender.send(message);
    }

    public void sendLowStockAlert(List<Stock> lowStockItems) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(myEmail);
        message.setTo(myEmail);
        message.setSubject("Low Stock Alert");

        String lowStockDetails = lowStockItems.stream()
                .map(stock -> "Product ID: " + stock.getProductId() + ", Quantity: " + stock.getQuantity())
                .collect(Collectors.joining("\n"));

        message.setText("The following items are low in stock:\n\n" + lowStockDetails);

        mailSender.send(message);
    }
}
