package com.example.order_system.controller;

import com.example.order_system.dto.CustomerDto;
import com.example.order_system.dto.response.ResponseCustomerLastOrders;
import com.example.order_system.dto.response.ResponseCustomerNumberOfProductsInYear;
import com.example.order_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers_without_orders")
    ResponseEntity<List<CustomerDto>> getCustomersWithoutOrders() {
        return customerService.getCustomersWithoutOrders();
    }

    @GetMapping("/customers_last_orders")
    ResponseEntity<List<ResponseCustomerLastOrders>> getCustomersLastOrders() {
        return customerService.getCustomersLastOrders();
    }

    @GetMapping("/number_of_products_in_year")
    List<ResponseCustomerNumberOfProductsInYear> getTotalNumber(){
        return customerService.getTotalNumberOfProducts();
    }
}
