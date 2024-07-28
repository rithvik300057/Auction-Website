package com.BITSBids.BITSBids.service;

import com.BITSBids.BITSBids.model.Product;
import com.BITSBids.BITSBids.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    // Add a new product to the database
    public Product addProduct(Product product) {
        // Generate a unique ID for the product
        String productId = UUID.randomUUID().toString();
        product.setPid(productId);
        // Save the product to the repository
        return repository.save(product);
    }

    // Retrieve a list of all products from the database
    public List<Product> findProducts() {
        return repository.findAll();
    }

    // Retrieve a product by its unique ID
    public Optional<Product> getProductBypid(String pid) {
        if (pid != null) {
            return repository.findById(pid);
        }
        return Optional.empty();
    }

    // Retrieve a list of products by name
    public List<Product> getProductsByName(String name) {
        return repository.findByname(name);
    }

    // Update an existing product's information by its ID
    public Optional<Product> updateProduct(String pid, Product productRequest) {
        Optional<Product> existingProductOptional = repository.findById(pid);
        if (existingProductOptional.isPresent()) {
            // Get the existing product from the database
            Product existingProduct = existingProductOptional.get();
            // Update the product's properties with the new data
            existingProduct.setName(productRequest.getName());
            existingProduct.setCategory(productRequest.getCategory());
            existingProduct.setDescription(productRequest.getDescription());
            existingProduct.setCurrentBid(productRequest.getCurrentBid());
            existingProduct.setTimeLeft(productRequest.getTimeLeft());
            // Save the updated product to the repository
            return Optional.of(repository.save(existingProduct));
        }
        return Optional.empty(); // Product not found
    }

    // Delete a product by its unique ID
    public boolean deleteProduct(String pid) {
        Optional<Product> product = repository.findById(pid);
        if (product.isPresent()) {
            // Delete the product from the repository
            repository.deleteById(pid);
            return true; // Product was found and deleted
        }
        return false; // Product not found
    }
}
