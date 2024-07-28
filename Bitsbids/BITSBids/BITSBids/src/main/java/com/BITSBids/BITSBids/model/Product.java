package com.BITSBids.BITSBids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String pid;
    private String name;
    private String category;
    private String description;
    private double currentBid; // Add currentBid property
    private String timeLeft; // Add timeLeft property
}

