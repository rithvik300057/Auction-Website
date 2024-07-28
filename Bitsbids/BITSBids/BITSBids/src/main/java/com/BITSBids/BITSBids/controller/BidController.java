package com.BITSBids.BITSBids.controller;

import com.BITSBids.BITSBids.model.Bid;
import com.BITSBids.BITSBids.service.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping("/bid")
@CrossOrigin
public class BidController {
    private final BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @PostMapping
    public ResponseEntity<?> createBid(@RequestBody Bid bid) {
        if (bid.getUid() == null || bid.getUid().isEmpty()) {
            // Return a ResponseEntity with a bad request status and an error message.
            return ResponseEntity.badRequest().body("User ID (uid) cannot be null or empty.");
        }

        try {
            Bid createdBid = bidService.addBid(bid.getPid(), bid.getUid(), bid.getAmount());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBid);
        } catch (IllegalArgumentException e) {
            // Handle other exceptions if needed.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }


    @GetMapping
    public ResponseEntity<List<Bid>> getAllBids() {
        List<Bid> bids = bidService.getAllBids();
        return ResponseEntity.ok(bids);
    }

    @GetMapping("/{bidId}")
    public ResponseEntity<Bid> getBidById(@PathVariable String bidId) {
        Bid bid = bidService.getBidById(bidId);
        if (bid != null) {
            return ResponseEntity.ok(bid);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bidId}")
    public ResponseEntity<String> deleteBid(@PathVariable String bidId) {
        boolean bidDeleted = bidService.deleteBid(bidId);
        if (bidDeleted) {
            return ResponseEntity.ok("Bid with ID " + bidId + " deleted.");
        } else {
            // Return a response with a 404 (NOT FOUND) status code and a message
            return new ResponseEntity<>("Product with ID " + bidId + " Not Found", HttpStatus.NOT_FOUND);
        }
    }
}