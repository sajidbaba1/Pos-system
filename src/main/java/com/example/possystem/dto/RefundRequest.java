package com.example.possystem.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RefundRequest {
    private Long orderId;
    private Long cashierId;
    private Long branchId;
    private BigDecimal amount;
    private String reason;
}
