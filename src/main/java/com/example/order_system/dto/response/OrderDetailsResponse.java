package com.example.order_system.dto.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class OrderDetailsResponse {
    private LocalDate date;
    private String customerName;
    private String customerCountry;
    private String customerAddress;
    private Integer customerPhone;
    private String productName;
    private Integer amount;
}
