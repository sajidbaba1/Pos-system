package com.example.possystem.dto;

import com.example.possystem.domain.Role;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Role role;
    private Long storeId;
    private Long branchId;
}
