package com.example.possystem.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
