package com.block5.block5profiles;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class Block5ProfilesApplication implements CommandLineRunner {
private final AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(Block5ProfilesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("My app name: {}. BD url: {}", appConfig.getName(), appConfig.getBdurl());
	}
}
