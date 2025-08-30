package com.example.possystem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String workingDays;
    private LocalTime openTime;
    private LocalTime closeTime;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
