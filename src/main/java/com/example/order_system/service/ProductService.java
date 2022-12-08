package com.example.order_system.service;

import com.example.order_system.dto.ProductDto;
import com.example.order_system.dto.response.ResponseBulkProducts;
import com.example.order_system.dto.response.ResponsePaymentHightDemandProducts;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {


    ResponseEntity<List<ProductDto>> getUsers();

    ResponseEntity<ProductDto> getProductId(Long id);

    List<ResponsePaymentHightDemandProducts> getHightDemandProducts();

    List<ResponseBulkProducts> getBulkProducts();
}
