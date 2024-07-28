package com.BITSBids.BITSBids.repository;

import com.BITSBids.BITSBids.model.Product;
import com.BITSBids.BITSBids.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    @Query("{uname: ?0 }")
    List<Product> findByUname(String uname);

}
