package com.BITSBids.BITSBids.repository;

import com.BITSBids.BITSBids.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
    @Query("{name: ?0 }")
   List<Product> findByname(String name);
}
