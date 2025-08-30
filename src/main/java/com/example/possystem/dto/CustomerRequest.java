package com.example.possystem.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String fullName;
    private String email;
    private String phone;
}
