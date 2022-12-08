package com.example.order_system.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PaymentDto {
    private Long Id;
    private Timestamp time;
    private Integer amount;
    private Long invoiceId;
}