package com.example.possystem.controller;

import com.example.possystem.domain.Store;
import com.example.possystem.domain.User;
import com.example.possystem.dto.StoreRequest;
import com.example.possystem.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public Store createStore(@RequestBody StoreRequest storeRequest, @AuthenticationPrincipal User user) {
        Store store = new Store();
        store.setName(storeRequest.getName());
        store.setBrand(storeRequest.getBrand());
        store.setDescription(storeRequest.getDescription());
        store.setType(storeRequest.getType());
        store.setContact(storeRequest.getContact());
        store.setStoreAdmin(user);
        return storeService.createStore(store);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Long id) {
        return storeService.getStoreById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/admin")
    public List<Store> getStoresByAdmin(@AuthenticationPrincipal User user) {
        return storeService.getStoresByAdmin(user.getId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @RequestBody StoreRequest storeRequest) {
        Store storeDetails = new Store();
        storeDetails.setName(storeRequest.getName());
        storeDetails.setBrand(storeRequest.getBrand());
        storeDetails.setDescription(storeRequest.getDescription());
        storeDetails.setType(storeRequest.getType());
        storeDetails.setContact(storeRequest.getContact());
        return ResponseEntity.ok(storeService.updateStore(id, storeDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }

    // The /api/stores/employee and /api/stores/{id}/moderate endpoints will be implemented later
    // as they require more complex logic involving employee roles and store moderation status.
}
