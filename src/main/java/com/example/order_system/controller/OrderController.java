package com.example.order_system.controller;

import com.example.order_system.dto.OrderDto;
import com.example.order_system.dto.request.CreateOrder;
import com.example.order_system.dto.response.OrderDetailsResponse;
import com.example.order_system.dto.response.ResponseBulkProducts;
import com.example.order_system.dto.response.ResponseOrder;
import com.example.order_system.dto.response.ResponseOrderWithoutInvoices;
import com.example.order_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    ResponseEntity<ResponseOrder> creatingNewOrder (@RequestBody CreateOrder dto){
        ResponseOrder responseOrder = orderService.createOrder(dto);
        return ResponseEntity.ok(responseOrder);
    }

    @GetMapping("/details")
    public OrderDetailsResponse getOrderId(@RequestParam("order_id") Long orderId){
        return orderService.getOrderId(orderId);
    }

    @GetMapping("/orders_without_details")
    ResponseEntity<List<OrderDto>> getOrdersWithoutDetails() {
        return orderService.getOrdersWithoutDetails();
    }
    @GetMapping("/orders_without_invoices")
    List<ResponseOrderWithoutInvoices> getWithoutInvoices(){
        return orderService.getOrdersWithoutInvoices();
    }
}
