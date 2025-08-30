package com.example.possystem.dto;

import lombok.Data;

@Data
public class StoreRequest {
    private String name;
    private String brand;
    private String description;
    private String type;
    private String contact;
}
