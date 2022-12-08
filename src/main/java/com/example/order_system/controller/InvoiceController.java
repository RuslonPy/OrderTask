package com.example.order_system.controller;

import com.example.order_system.dto.InvoiceDto;
import com.example.order_system.dto.response.ResponceInvoice;
import com.example.order_system.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/expired_invoices")
    ResponseEntity<List<InvoiceDto>> getExpiredUsers() {
        return invoiceService.getExpiredUsers();
    }

    @GetMapping("/wrong_date_invoices")
    List<ResponceInvoice> getWrongDateInvoices(){
        return invoiceService.getWrongDateInvoices();
    }
}
