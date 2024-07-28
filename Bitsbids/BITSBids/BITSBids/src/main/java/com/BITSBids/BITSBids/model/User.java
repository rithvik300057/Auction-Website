package com.BITSBids.BITSBids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String uid;
    private String uname;
    private String ubatch;
    private Integer ucontact;
    private String uemail;
}
