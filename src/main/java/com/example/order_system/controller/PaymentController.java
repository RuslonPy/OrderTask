package com.example.order_system.controller;

import com.example.order_system.dto.PaymentDto;
import com.example.order_system.dto.request.RequestPayment;
import com.example.order_system.dto.response.ResponsePayment;
import com.example.order_system.dto.response.ResponsePaymentOverpaid;
import com.example.order_system.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("")
    ResponseEntity<ResponsePayment> createNewOrder (@RequestParam("invoice_id") Long invoiceId){
        ResponsePayment  responsePayment = paymentService.createNewUser(invoiceId);
        return ResponseEntity.ok(responsePayment);
    }

    @GetMapping("/details")
    public ResponseEntity<PaymentDto> getId(@RequestParam("id") Long payment_details_id){
        return paymentService.getPaymentId(payment_details_id);
    }

    @PostMapping("pay_invoice")
    ResponseEntity<PaymentDto> creatingNewPayment (@RequestBody RequestPayment dto){
        PaymentDto paymentDto = paymentService.createPayment(dto);
        return ResponseEntity.ok(paymentDto);
    }

    @GetMapping("/overpaid_invoice_id")
    public ResponseEntity<ResponsePaymentOverpaid> getOverpaid(@RequestParam("invoice_id") Long invoiceId){
        return paymentService.getPayOverpaid(invoiceId);
    }

    @GetMapping("/overpaid_invoice")
    List<ResponsePaymentOverpaid> getresponsePaymentOverpaids(){
        return paymentService.getOverpaidInvoices();
    }
}
