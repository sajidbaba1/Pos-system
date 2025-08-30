package com.example.possystem.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private int quantity;
    private Long productId;
    private Long branchId;
}
