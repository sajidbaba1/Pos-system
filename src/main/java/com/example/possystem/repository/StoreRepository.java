package com.example.possystem.repository;

import com.example.possystem.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findByStoreAdminId(Long adminId);
}
