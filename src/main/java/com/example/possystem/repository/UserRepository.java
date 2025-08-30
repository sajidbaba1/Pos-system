package com.example.possystem.repository;

import com.example.possystem.domain.Role;
import com.example.possystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByStoreId(Long storeId);
    List<User> findByBranchId(Long branchId);
    List<User> findByStoreIdAndRole(Long storeId, Role role);
    List<User> findByBranchIdAndRole(Long branchId, Role role);
}
