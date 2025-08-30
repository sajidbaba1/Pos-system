package com.example.possystem.repository;

import com.example.possystem.domain.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    List<Branch> findByStoreId(Long storeId);
}
