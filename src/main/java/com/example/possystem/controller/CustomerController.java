package com.example.possystem.controller;

import com.example.possystem.domain.Customer;
import com.example.possystem.dto.CustomerRequest;
import com.example.possystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setFullName(customerRequest.getFullName());
        customer.setEmail(customerRequest.getEmail());
        customer.setPhone(customerRequest.getPhone());
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam("q") String keyword) {
        return customerService.searchCustomers(keyword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        Customer customerDetails = new Customer();
        customerDetails.setFullName(customerRequest.getFullName());
        customerDetails.setEmail(customerRequest.getEmail());
        customerDetails.setPhone(customerRequest.getPhone());
        return ResponseEntity.ok(customerService.updateCustomer(id, customerDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
