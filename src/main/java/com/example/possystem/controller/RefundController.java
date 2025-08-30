package com.example.possystem.controller;

import com.example.possystem.domain.Branch;
import com.example.possystem.domain.Order;
import com.example.possystem.domain.Refund;
import com.example.possystem.domain.User;
import com.example.possystem.dto.RefundRequest;
import com.example.possystem.service.BranchService;
import com.example.possystem.service.EmployeeService;
import com.example.possystem.service.OrderService;
import com.example.possystem.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    @PostMapping
    public Refund createRefund(@RequestBody RefundRequest refundRequest) {
        Order order = orderService.getOrderById(refundRequest.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        User cashier = employeeService.getEmployeeById(refundRequest.getCashierId())
                .orElseThrow(() -> new RuntimeException("Cashier not found"));
        Branch branch = branchService.getBranchById(refundRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Refund refund = new Refund();
        refund.setOrder(order);
        refund.setCashier(cashier);
        refund.setBranch(branch);
        refund.setAmount(refundRequest.getAmount());
        refund.setReason(refundRequest.getReason());

        return refundService.createRefund(refund);
    }

    @GetMapping
    public List<Refund> getAllRefunds() {
        return refundService.getAllRefunds();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Refund> getRefundById(@PathVariable Long id) {
        return refundService.getRefundById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cashier/{cashierId}")
    public List<Refund> getRefundsByCashier(@PathVariable Long cashierId) {
        return refundService.getRefundsByCashier(cashierId);
    }

    @GetMapping("/branch/{branchId}")
    public List<Refund> getRefundsByBranch(@PathVariable Long branchId) {
        return refundService.getRefundsByBranch(branchId);
    }

    @GetMapping("/cashier/{cashierId}/range")
    public List<Refund> getRefundsByCashierAndDateRange(
            @PathVariable Long cashierId,
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        return refundService.getRefundsByCashierAndDateRange(cashierId, startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefund(@PathVariable Long id) {
        refundService.deleteRefund(id);
        return ResponseEntity.noContent().build();
    }
}
