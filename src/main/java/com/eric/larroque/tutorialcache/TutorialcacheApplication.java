package com.eric.larroque.tutorialcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TutorialcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutorialcacheApplication.class, args);
	}

}
