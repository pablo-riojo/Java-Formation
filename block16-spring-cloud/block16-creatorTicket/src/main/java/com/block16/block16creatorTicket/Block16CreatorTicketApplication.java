package com.block16.block16creatorTicket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class Block16CreatorTicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block16CreatorTicketApplication.class, args);
	}

}
