package com.example.order_system.dto.response;

import lombok.Data;

@Data
public class ResponsePaymentHightDemandProducts {
    private Long productCode;
    private String productName;
    private Integer totalNumber;
}