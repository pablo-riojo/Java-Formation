package com.block5.block5commandlinerunner;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Class1 {
    @PostConstruct
    public static void init(){
        System.out.println("Initial class");
    }
}
