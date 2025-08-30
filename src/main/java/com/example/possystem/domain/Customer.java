package com.example.possystem.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
}
