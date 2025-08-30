package com.example.possystem.dto;

import com.example.possystem.domain.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Role role;
}
