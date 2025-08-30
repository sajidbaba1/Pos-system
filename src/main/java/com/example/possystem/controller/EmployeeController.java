package com.example.possystem.controller;

import com.example.possystem.domain.Branch;
import com.example.possystem.domain.Role;
import com.example.possystem.domain.Store;
import com.example.possystem.domain.User;
import com.example.possystem.dto.EmployeeRequest;
import com.example.possystem.service.BranchService;
import com.example.possystem.service.EmployeeService;
import com.example.possystem.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/store/{storeId}")
    public User createStoreEmployee(@PathVariable Long storeId, @RequestBody EmployeeRequest employeeRequest) {
        Store store = storeService.getStoreById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        User user = new User();
        user.setUsername(employeeRequest.getUsername());
        user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        user.setEmail(employeeRequest.getEmail());
        user.setFullName(employeeRequest.getFullName());
        user.setRole(employeeRequest.getRole());
        user.setStore(store);

        return employeeService.createEmployee(user);
    }

    @PostMapping("/branch/{branchId}")
    public User createBranchEmployee(@PathVariable Long branchId, @RequestBody EmployeeRequest employeeRequest) {
        Branch branch = branchService.getBranchById(branchId)
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        User user = new User();
        user.setUsername(employeeRequest.getUsername());
        user.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        user.setEmail(employeeRequest.getEmail());
        user.setFullName(employeeRequest.getFullName());
        user.setRole(employeeRequest.getRole());
        user.setStore(branch.getStore());
        user.setBranch(branch);

        return employeeService.createEmployee(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        User userDetails = new User();
        userDetails.setUsername(employeeRequest.getUsername());
        if (employeeRequest.getPassword() != null && !employeeRequest.getPassword().isEmpty()) {
            userDetails.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
        }
        userDetails.setEmail(employeeRequest.getEmail());
        userDetails.setFullName(employeeRequest.getFullName());
        userDetails.setRole(employeeRequest.getRole());

        if (employeeRequest.getStoreId() != null) {
            Store store = storeService.getStoreById(employeeRequest.getStoreId())
                    .orElseThrow(() -> new RuntimeException("Store not found"));
            userDetails.setStore(store);
        }
        if (employeeRequest.getBranchId() != null) {
            Branch branch = branchService.getBranchById(employeeRequest.getBranchId())
                    .orElseThrow(() -> new RuntimeException("Branch not found"));
            userDetails.setBranch(branch);
        }

        return ResponseEntity.ok(employeeService.updateEmployee(id, userDetails));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/store/{storeId}")
    public List<User> getEmployeesByStore(@PathVariable Long storeId, @RequestParam(required = false) Role role) {
        return employeeService.getEmployeesByStore(storeId, role);
    }

    @GetMapping("/branch/{branchId}")
    public List<User> getEmployeesByBranch(@PathVariable Long branchId, @RequestParam(required = false) Role role) {
        return employeeService.getEmployeesByBranch(branchId, role);
    }
}
