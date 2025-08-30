package com.example.possystem.controller;

import com.example.possystem.domain.Category;
import com.example.possystem.domain.Store;
import com.example.possystem.dto.CategoryRequest;
import com.example.possystem.service.CategoryService;
import com.example.possystem.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public Category createCategory(@RequestBody CategoryRequest categoryRequest) {
        Store store = storeService.getStoreById(categoryRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setStore(store);
        return categoryService.createCategory(category);
    }

    @GetMapping("/store/{storeId}")
    public List<Category> getCategoriesByStore(@PathVariable Long storeId) {
        return categoryService.getAllCategoriesByStore(storeId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Store store = storeService.getStoreById(categoryRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Category categoryDetails = new Category();
        categoryDetails.setName(categoryRequest.getName());
        categoryDetails.setStore(store);
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
