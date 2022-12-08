package com.example.order_system.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class RequestPayment {

    private Integer amount;
    @JsonProperty("invoice_id")
    private Long invoiceId;
}
