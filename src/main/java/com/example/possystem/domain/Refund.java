package com.example.possystem.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "refunds")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    private String reason;
    private LocalDateTime refundDate;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private User cashier;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
