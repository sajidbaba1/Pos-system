package com.example.possystem.controller;

import com.example.possystem.domain.Branch;
import com.example.possystem.domain.Inventory;
import com.example.possystem.domain.Product;
import com.example.possystem.dto.InventoryRequest;
import com.example.possystem.service.BranchService;
import com.example.possystem.service.InventoryService;
import com.example.possystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private BranchService branchService;

    @PostMapping
    public Inventory createInventory(@RequestBody InventoryRequest inventoryRequest) {
        Product product = productService.getProductById(inventoryRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Branch branch = branchService.getBranchById(inventoryRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Inventory inventory = new Inventory();
        inventory.setQuantity(inventoryRequest.getQuantity());
        inventory.setProduct(product);
        inventory.setBranch(branch);

        return inventoryService.createInventory(inventory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryService.getInventoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/branch/{branchId}")
    public List<Inventory> getInventoryByBranch(@PathVariable Long branchId) {
        return inventoryService.getAllInventoryByBranch(branchId);
    }

    @GetMapping("/product/{productId}/branch/{branchId}")
    public ResponseEntity<Inventory> getInventoryByProductAndBranch(@PathVariable Long productId, @PathVariable Long branchId) {
        return inventoryService.getInventoryByProductAndBranch(productId, branchId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody InventoryRequest inventoryRequest) {
        Product product = productService.getProductById(inventoryRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Branch branch = branchService.getBranchById(inventoryRequest.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));

        Inventory inventoryDetails = new Inventory();
        inventoryDetails.setQuantity(inventoryRequest.getQuantity());
        inventoryDetails.setProduct(product);
        inventoryDetails.setBranch(branch);

        return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }
}
