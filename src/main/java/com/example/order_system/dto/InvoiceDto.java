package com.example.order_system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class InvoiceDto {
    private Long Id;
    private int amount;
    private LocalDate issued;
    private LocalDate due;
    private Long orderId;
}
