package com.example.order_system.service;

import com.example.order_system.dto.CustomerDto;
import com.example.order_system.dto.response.ResponseCustomerLastOrders;
import com.example.order_system.dto.response.ResponseCustomerNumberOfProductsInYear;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CustomerService {
    ResponseEntity<List<CustomerDto>> getCustomersWithoutOrders();

    ResponseEntity<List<ResponseCustomerLastOrders>> getCustomersLastOrders();

    List<ResponseCustomerNumberOfProductsInYear> getTotalNumberOfProducts();
}
