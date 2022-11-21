package com.block5.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Block5PropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block5PropertiesApplication.class, args);
	}

@Component
public static class CLR implements CommandLineRunner {
		@Value("${greeting}")
		private String name;

		@Value("${myNumber}")
		private int number;

		@Value("${new.property:newProperty not found}")
		private String newProperty;

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getNewProperty() {
		return newProperty;
	}

	@Override
		public void run(String ...args) {
			System.out.println("El valor de greeting es: " + getName());
			System.out.println("El valor de number es: " + getNumber());
			System.out.println("El valor de new property es: " + getNewProperty());
		}
	}

}
