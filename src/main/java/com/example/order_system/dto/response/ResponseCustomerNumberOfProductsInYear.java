package com.example.order_system.dto.response;

import lombok.Data;

@Data
public class ResponseCustomerNumberOfProductsInYear {
    private String country;
    private Integer totalNumberOfOrders;
}
