package com.example.possystem.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stores")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String description;
    private String type;
    private String contact;

    @ManyToOne
    @JoinColumn(name = "store_admin_id")
    private User storeAdmin;
}
