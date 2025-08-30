package com.example.possystem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private String sku;
    private String brand;
    private String image;
    private BigDecimal price;
    private Long categoryId;
    private Long storeId;
}
