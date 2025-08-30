package com.example.possystem.controller;

import com.example.possystem.domain.*;
import com.example.possystem.dto.OrderRequest;
import com.example.possystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProductService productService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest orderRequest) {
        Branch branch = branchService.getBranchById(orderRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
        Customer customer = customerService.getCustomerById(orderRequest.getCustomerId())
                .orElse(null); // Customer can be optional
        User cashier = employeeService.getEmployeeById(orderRequest.getCashierId())
                .orElseThrow(() -> new RuntimeException("Cashier not found"));

        Order order = new Order();
        order.setBranch(branch);
        order.setCustomer(customer);
        order.setCashier(cashier);
        order.setStatus(orderRequest.getStatus());
        order.setPaymentType(orderRequest.getPaymentType());

        List<OrderItem> orderItems = orderRequest.getOrderItems().stream().map(itemRequest -> {
            Product product = productService.getProductById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/branch/{branchId}")
    public List<Order> getOrdersByBranch(@PathVariable Long branchId) {
        return orderService.getOrdersByBranch(branchId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomer(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomer(customerId);
    }

    @GetMapping("/cashier/{cashierId}")
    public List<Order> getOrdersByCashier(@PathVariable Long cashierId) {
        return orderService.getOrdersByCashier(cashierId);
    }

    @GetMapping("/today/branch/{branchId}")
    public List<Order> getTodaysOrdersByBranch(@PathVariable Long branchId) {
        return orderService.getTodaysOrdersByBranch(branchId);
    }

    @GetMapping("/recent/branch/{branchId}")
    public List<Order> getRecentOrdersByBranch(@PathVariable Long branchId) {
        return orderService.getRecentOrdersByBranch(branchId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
