package com.block5.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Class2 {
    @Bean
     CommandLineRunner second() {
        return x -> System.out.println("Second class");
    }
}