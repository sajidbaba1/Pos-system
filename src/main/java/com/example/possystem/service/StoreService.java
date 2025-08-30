package com.example.possystem.service;

import com.example.possystem.domain.Store;
import com.example.possystem.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store createStore(Store store) {
        return storeRepository.save(store);
    }

    public Optional<Store> getStoreById(Long id) {
        return storeRepository.findById(id);
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public List<Store> getStoresByAdmin(Long adminId) {
        return storeRepository.findByStoreAdminId(adminId);
    }

    public Store updateStore(Long id, Store storeDetails) {
        Store store = storeRepository.findById(id).orElseThrow(() -> new RuntimeException("Store not found"));
        store.setName(storeDetails.getName());
        store.setBrand(storeDetails.getBrand());
        store.setDescription(storeDetails.getDescription());
        store.setType(storeDetails.getType());
        store.setContact(storeDetails.getContact());
        return storeRepository.save(store);
    }

    public void deleteStore(Long id) {
        storeRepository.deleteById(id);
    }
}
