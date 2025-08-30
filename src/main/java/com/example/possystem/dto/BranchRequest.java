package com.example.possystem.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class BranchRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String workingDays;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Long storeId;
}
