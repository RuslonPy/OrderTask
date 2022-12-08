package com.example.order_system.dto;

import lombok.Data;

@Data
public class DetailDto {
    private Long Id;
    private Integer quantity;
    private Long orderId;
    private Long productId;
}
