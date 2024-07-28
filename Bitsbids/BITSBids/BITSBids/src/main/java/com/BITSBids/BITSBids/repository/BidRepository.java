package com.BITSBids.BITSBids.repository;

import com.BITSBids.BITSBids.model.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BidRepository extends MongoRepository<Bid, String> {

}
