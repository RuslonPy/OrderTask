package com.example.order_system.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long Id;
    private String name;
    private String description;
    private int numeric;
    private String phone;
    private Long categoryId;
}
