package com.BITSBids.BITSBids.service;

import com.BITSBids.BITSBids.model.Bid;
import com.BITSBids.BITSBids.model.Product;
import com.BITSBids.BITSBids.model.User;
import com.BITSBids.BITSBids.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    private final BidRepository bidRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public BidService(BidRepository bidRepository, ProductService productService, UserService userService) {
        this.bidRepository = bidRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public Bid addBid(String pid, String uid, double amount) {
        // Check if uid is null or empty
        if (uid == null || uid.isEmpty()) {
            throw new IllegalArgumentException("User ID (uid) cannot be null or empty");
        }

        Optional<Product> product = productService.getProductBypid(pid);
        Optional<User> user = userService.getUserById(uid);

        if (product.isPresent() && user.isPresent()) {
            Bid bid = new Bid();
            bid.setPid(pid);
            bid.setUid(uid);
            bid.setAmount(amount);
            return bidRepository.save(bid);
        } else {
            // Handle the case where product or user was not found
            throw new RuntimeException("Product or User not found");
        }
    }


    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public Bid getBidById(String bidId) {
        return bidRepository.findById(bidId).orElse(null);
    }

    public boolean deleteBid(String bidId) {
        Optional<Bid> bid = bidRepository.findById(bidId);
        if (bid.isPresent()) {
            bidRepository.deleteById(bidId);
            return true;
        }
        return false;
    }

}
