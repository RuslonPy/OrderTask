package com.example.order_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseOrderWithoutInvoices {
    private Long orderId;
    private LocalDate orderDate;
    private Integer totalPrice;
}
