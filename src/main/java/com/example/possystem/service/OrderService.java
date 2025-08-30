package com.example.possystem.service;

import com.example.possystem.domain.Order;
import com.example.possystem.domain.OrderItem;
import com.example.possystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        order.setOrderDate(LocalDateTime.now());
        BigDecimal totalAmount = order.getOrderItems().stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        for(OrderItem item : order.getOrderItems()) {
            item.setOrder(order);
        }

        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByBranch(Long branchId) {
        return orderRepository.findByBranchId(branchId);
    }

    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public List<Order> getOrdersByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId);
    }

    public List<Order> getTodaysOrdersByBranch(Long branchId) {
        return orderRepository.findTodaysOrdersByBranch(branchId, LocalDate.now().atStartOfDay());
    }

    public List<Order> getRecentOrdersByBranch(Long branchId) {
        // This should be limited. I will handle this in the controller or by using Pageable.
        return orderRepository.findRecentOrdersByBranch(branchId);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
