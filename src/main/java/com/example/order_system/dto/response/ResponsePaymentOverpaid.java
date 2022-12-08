package com.example.order_system.dto.response;

import lombok.Data;

@Data
public class ResponsePaymentOverpaid {
    private Long invoiceId;
    private Integer amount;
}
