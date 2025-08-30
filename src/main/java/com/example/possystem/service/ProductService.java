package com.example.possystem.service;

import com.example.possystem.domain.Product;
import com.example.possystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProductsByStore(Long storeId) {
        return productRepository.findByStoreId(storeId);
    }

    public List<Product> searchProductsInStore(Long storeId, String keyword) {
        return productRepository.searchByKeywordInStore(storeId, keyword);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setSku(productDetails.getSku());
        product.setBrand(productDetails.getBrand());
        product.setImage(productDetails.getImage());
        product.setPrice(productDetails.getPrice());
        product.setCategory(productDetails.getCategory());
        product.setStore(productDetails.getStore());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
