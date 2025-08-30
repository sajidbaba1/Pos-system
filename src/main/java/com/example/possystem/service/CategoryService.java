package com.example.possystem.service;

import com.example.possystem.domain.Category;
import com.example.possystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategoriesByStore(Long storeId) {
        return categoryRepository.findByStoreId(storeId);
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        category.setName(categoryDetails.getName());
        category.setStore(categoryDetails.getStore());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
