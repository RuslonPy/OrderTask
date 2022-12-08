package com.example.order_system.controller;

import com.example.order_system.dto.ProductDto;
import com.example.order_system.dto.response.ResponseBulkProducts;
import com.example.order_system.dto.response.ResponsePaymentHightDemandProducts;
import com.example.order_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductsController {

    @Autowired
    private ProductService productService;


    @GetMapping("/list")
    ResponseEntity<List<ProductDto>> getUser() {
        return productService.getUsers();
    }

    @GetMapping("/details")
    public ResponseEntity<ProductDto> getProductId(@RequestParam("product_id") Long productId){
        return productService.getProductId(productId);
    }


    @GetMapping("/high_demand_products")
    List<ResponsePaymentHightDemandProducts> getHightDemandProducts(){
        return productService.getHightDemandProducts();
    }

    @GetMapping("/bulk_products")
    List<ResponseBulkProducts> getBulkProducts(){
        return productService.getBulkProducts();
    }
}
