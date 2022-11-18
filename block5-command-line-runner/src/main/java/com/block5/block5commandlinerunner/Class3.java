package com.block5.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Class3 implements CommandLineRunner {
    @Override
public void run(String ...args) {
        System.out.println("Third class");
    }
}
