package com.block7.block7crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class}
)
public class Block7CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudApplication.class, args);
	}

}
