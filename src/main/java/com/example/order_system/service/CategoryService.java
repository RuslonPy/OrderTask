package com.example.order_system.service;

import com.example.order_system.dto.CategoryDto;
import com.example.order_system.dto.ProductDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<List<CategoryDto>> getUsers();


    ResponseEntity<CategoryDto> getUser(Long id);

}
