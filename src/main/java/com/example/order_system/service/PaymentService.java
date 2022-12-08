package com.example.order_system.service;

import com.example.order_system.dto.PaymentDto;
import com.example.order_system.dto.request.RequestPayment;
import com.example.order_system.dto.response.ResponsePayment;
import com.example.order_system.dto.response.ResponsePaymentOverpaid;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaymentService {

    ResponsePayment createNewUser(Long invoiceId);

    ResponseEntity<PaymentDto> getPaymentId(Long payment_details_id);

    PaymentDto createPayment(RequestPayment dto);

    ResponseEntity<ResponsePaymentOverpaid> getPayOverpaid(Long invoiceId);

    List<ResponsePaymentOverpaid> getOverpaidInvoices();
}
