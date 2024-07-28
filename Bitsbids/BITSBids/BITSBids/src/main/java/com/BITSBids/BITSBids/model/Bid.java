package com.BITSBids.BITSBids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bid")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bid {
    @Id
    private String bidid;
    private String pid;
    private String uid;
    private double amount;

}

