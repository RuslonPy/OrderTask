package com.example.order_system.service;

import com.example.order_system.dto.OrderDto;
import com.example.order_system.dto.request.CreateOrder;
import com.example.order_system.dto.response.OrderDetailsResponse;
import com.example.order_system.dto.response.ResponseOrder;
import com.example.order_system.dto.response.ResponseOrderWithoutInvoices;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface OrderService {
    ResponseOrder createOrder(CreateOrder dto);

    OrderDetailsResponse getOrderId(Long orderId);

    ResponseEntity<List<OrderDto>> getOrdersWithoutDetails();

    List<ResponseOrderWithoutInvoices> getOrdersWithoutInvoices();
}
