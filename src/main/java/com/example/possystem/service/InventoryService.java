package com.example.possystem.service;

import com.example.possystem.domain.Inventory;
import com.example.possystem.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventoryByBranch(Long branchId) {
        return inventoryRepository.findByBranchId(branchId);
    }

    public Optional<Inventory> getInventoryByProductAndBranch(Long productId, Long branchId) {
        return inventoryRepository.findByProductIdAndBranchId(productId, branchId);
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventoryDetails) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
        inventory.setQuantity(inventoryDetails.getQuantity());
        inventory.setProduct(inventoryDetails.getProduct());
        inventory.setBranch(inventoryDetails.getBranch());
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
