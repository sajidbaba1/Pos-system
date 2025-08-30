package com.example.possystem.dto;

import com.example.possystem.domain.Order;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long branchId;
    private Long customerId;
    private Long cashierId;
    private Order.OrderStatus status;
    private Order.PaymentType paymentType;
    private List<OrderItemRequest> orderItems;
}
