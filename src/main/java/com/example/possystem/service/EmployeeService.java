package com.example.possystem.service;

import com.example.possystem.domain.Role;
import com.example.possystem.domain.User;
import com.example.possystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private UserRepository userRepository;

    public User createEmployee(User user) {
        // Password should be encoded before calling this method
        return userRepository.save(user);
    }

    public Optional<User> getEmployeeById(Long id) {
        return userRepository.findById(id);
    }

    public User updateEmployee(Long id, User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(userDetails.getUsername());
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(userDetails.getPassword()); // Assume password is pre-encoded
        }
        user.setEmail(userDetails.getEmail());
        user.setFullName(userDetails.getFullName());
        user.setRole(userDetails.getRole());
        user.setStore(userDetails.getStore());
        user.setBranch(userDetails.getBranch());
        return userRepository.save(user);
    }

    public void deleteEmployee(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getEmployeesByStore(Long storeId, Role role) {
        if (role != null) {
            return userRepository.findByStoreIdAndRole(storeId, role);
        }
        return userRepository.findByStoreId(storeId);
    }

    public List<User> getEmployeesByBranch(Long branchId, Role role) {
        if (role != null) {
            return userRepository.findByBranchIdAndRole(branchId, role);
        }
        return userRepository.findByBranchId(branchId);
    }
}
