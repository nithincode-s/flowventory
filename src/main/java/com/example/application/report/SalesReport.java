package com.example.application.report;

import com.example.domain.order.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalesReport {
    private List<Order> orderList;
    private String reportDate;
}