package com.example.possystem.controller;

import com.example.possystem.domain.Branch;
import com.example.possystem.domain.Store;
import com.example.possystem.dto.BranchRequest;
import com.example.possystem.service.BranchService;
import com.example.possystem.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public Branch createBranch(@RequestBody BranchRequest branchRequest) {
        Store store = storeService.getStoreById(branchRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Branch branch = new Branch();
        branch.setName(branchRequest.getName());
        branch.setAddress(branchRequest.getAddress());
        branch.setPhone(branchRequest.getPhone());
        branch.setEmail(branchRequest.getEmail());
        branch.setWorkingDays(branchRequest.getWorkingDays());
        branch.setOpenTime(branchRequest.getOpenTime());
        branch.setCloseTime(branchRequest.getCloseTime());
        branch.setStore(store);

        return branchService.createBranch(branch);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
        return branchService.getBranchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    public List<Branch> getBranchesByStore(@PathVariable Long storeId) {
        return branchService.getAllBranchesByStore(storeId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody BranchRequest branchRequest) {
        Store store = storeService.getStoreById(branchRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Branch branchDetails = new Branch();
        branchDetails.setName(branchRequest.getName());
        branchDetails.setAddress(branchRequest.getAddress());
        branchDetails.setPhone(branchRequest.getPhone());
        branchDetails.setEmail(branchRequest.getEmail());
        branchDetails.setWorkingDays(branchRequest.getWorkingDays());
        branchDetails.setOpenTime(branchRequest.getOpenTime());
        branchDetails.setCloseTime(branchRequest.getCloseTime());
        branchDetails.setStore(store);

        return ResponseEntity.ok(branchService.updateBranch(id, branchDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
