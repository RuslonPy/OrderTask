package com.example.order_system.service;

import com.example.order_system.dto.InvoiceDto;
import com.example.order_system.dto.response.ResponceInvoice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InvoiceService {
    ResponseEntity<List<InvoiceDto>> getExpiredUsers();

    List<ResponceInvoice> getWrongDateInvoices();
}
