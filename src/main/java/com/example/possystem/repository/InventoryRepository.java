package com.example.possystem.repository;

import com.example.possystem.domain.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByBranchId(Long branchId);
    Optional<Inventory> findByProductIdAndBranchId(Long productId, Long branchId);
}
