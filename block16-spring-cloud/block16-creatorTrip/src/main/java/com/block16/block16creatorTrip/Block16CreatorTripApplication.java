package com.block16.block16creatorTrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Block16CreatorTripApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16CreatorTripApplication.class, args);
	}

}
