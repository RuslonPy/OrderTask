package com.example.order_system.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponceInvoice {
    private Long invoiceId;
    private Long orderId;
    private LocalDate invoiceIssued;
    private LocalDate orderDate;
}
