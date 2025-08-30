package com.example.possystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemRequest {
    private Long productId;
    private int quantity;
    private BigDecimal price; // Price at the time of order
}
