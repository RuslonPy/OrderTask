package com.example.order_system.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class OrderDto {
    private Long Id;
    private LocalDate date;
    private Long customerId;
    private String productName;
}
