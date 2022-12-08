package com.example.order_system.controller;


import com.example.order_system.dto.CategoryDto;
import com.example.order_system.service.CategoryService;
import com.example.order_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/all")
    ResponseEntity<List<CategoryDto>> getUser() {
        return categoryService.getUsers();
    }

    @GetMapping("")
    public ResponseEntity<CategoryDto> getUser(@RequestParam("product_id") Long id) {
        return categoryService.getUser(id);
    }

}
