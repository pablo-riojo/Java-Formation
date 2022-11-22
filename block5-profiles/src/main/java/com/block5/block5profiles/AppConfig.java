package com.block5.block5profiles;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

    @Data
    @Configuration
    public class AppConfig
    {
        @Value("${spring.profiles.active}")
        private String name;

        @Value("${bd.url}")
        private String bdurl;
    }

