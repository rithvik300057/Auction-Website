package com.BITSBids.BITSBids;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class BitsBidsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitsBidsApplication.class, args);
	}

}
