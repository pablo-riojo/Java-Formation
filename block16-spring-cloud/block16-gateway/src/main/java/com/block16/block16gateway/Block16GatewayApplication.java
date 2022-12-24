package com.block16.block16gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Block16GatewayApplication {
//	public static void main(String[] args) {
//		new SpringApplicationBuilder(Block16GatewayApplication.class)
//				.web(WebApplicationType.REACTIVE) // .REACTIVE, .SERVLET
//				.run(args);
//
//
//	}
		public static void main(String[] args) {
			SpringApplication.run(Block16GatewayApplication.class, args);
		}

}
