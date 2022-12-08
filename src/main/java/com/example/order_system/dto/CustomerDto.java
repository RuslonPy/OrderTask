package com.example.order_system.dto;

import lombok.Data;

@Data
public class CustomerDto {
    private Long Id;
    private String name;
    private String country;
    private String address;
    private Integer phone;
}
