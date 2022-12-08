package com.example.order_system.dto.response;

import lombok.Data;

@Data
public class ResponseOrder {
    private String status;
    private Long invoiceNumber;
}
