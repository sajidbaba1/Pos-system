package com.example.possystem.controller;

import com.example.possystem.domain.Category;
import com.example.possystem.domain.Product;
import com.example.possystem.domain.Store;
import com.example.possystem.dto.ProductRequest;
import com.example.possystem.service.CategoryService;
import com.example.possystem.service.ProductService;
import com.example.possystem.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StoreService storeService;

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        Store store = storeService.getStoreById(productRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Category category = categoryService.getCategoryById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setSku(productRequest.getSku());
        product.setBrand(productRequest.getBrand());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setCategory(category);
        product.setStore(store);

        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/store/{storeId}")
    public List<Product> getProductsByStore(@PathVariable Long storeId) {
        return productService.getAllProductsByStore(storeId);
    }

    @GetMapping("/search/{storeId}")
    public List<Product> searchProductsInStore(@PathVariable Long storeId, @RequestParam String keyword) {
        return productService.searchProductsInStore(storeId, keyword);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        Store store = storeService.getStoreById(productRequest.getStoreId())
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Category category = categoryService.getCategoryById(productRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product productDetails = new Product();
        productDetails.setName(productRequest.getName());
        productDetails.setSku(productRequest.getSku());
        productDetails.setBrand(productRequest.getBrand());
        productDetails.setImage(productRequest.getImage());
        productDetails.setPrice(productRequest.getPrice());
        productDetails.setCategory(category);
        productDetails.setStore(store);

        return ResponseEntity.ok(productService.updateProduct(id, productDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
