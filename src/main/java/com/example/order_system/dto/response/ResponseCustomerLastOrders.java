package com.example.order_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseCustomerLastOrders {
    private Long customerId;
    private String customerName;
    private LocalDate lastOrderDate;
}
