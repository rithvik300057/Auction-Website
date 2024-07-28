package com.BITSBids.BITSBids.controller;

import com.BITSBids.BITSBids.model.Product;
import com.BITSBids.BITSBids.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    // Endpoint to create a new product
    @PostMapping("/sell")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Call the service to add a product
        Product createdProduct = productService.addProduct(product);
        // Return a response with the created product and a 201 (CREATED) status code
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // Endpoint to get a list of all products
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        // Call the service to find all products
        List<Product> products = productService.findProducts();
        // Return a response with the list of products
        return ResponseEntity.ok(products);
    }

    // Endpoint to get a product by its unique ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductBypid(@PathVariable String id) {
        // Call the service to find a product by its ID
        Optional<Product> product = productService.getProductBypid(id);
        // Check if the product was found and return an appropriate response
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to get a list of products by name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
        // Call the service to find products by name
        List<Product> products = productService.getProductsByName(name);
        // Return a response with the list of products
        return ResponseEntity.ok(products);
    }

    // Endpoint to update an existing product by its ID
    @PutMapping("/{id}")
    public ResponseEntity<Product> modifyProduct(@PathVariable String id, @RequestBody Product product) {
        // Call the service to update a product
        Optional<Product> updatedProduct = productService.updateProduct(id, product);
        // Check if the product was found and updated, then return an appropriate response
        return updatedProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to remove a product by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable String id) {
        // Call the service to delete a product by its ID
        boolean productDeleted = productService.deleteProduct(id);
        // Check if the product was deleted and return an appropriate response
        if (productDeleted) {
            return ResponseEntity.ok("Product with ID " + id + " Product Deleted");
        } else {
            // Return a response with a 404 (NOT FOUND) status code and a message
            return new ResponseEntity<>("Product with ID " + id + " Not Found", HttpStatus.NOT_FOUND);
        }
    }
}

