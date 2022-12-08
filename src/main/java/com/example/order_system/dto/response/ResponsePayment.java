package com.example.order_system.dto.response;

import com.example.order_system.dto.PaymentDto;
import lombok.Data;

@Data
public class ResponsePayment {
    private PaymentDto payment;
    private String status;
}
