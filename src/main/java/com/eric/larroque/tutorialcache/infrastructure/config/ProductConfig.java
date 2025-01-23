package com.eric.larroque.tutorialcache.infrastructure.config;

import com.eric.larroque.tutorialcache.application.ProductService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    ApplicationRunner runner(ProductService productService) {
        return args -> {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("id: 1: " + productService.getById(1L));
            System.out.println("id: 2: " + productService.getById(2L));
            System.out.println("id: 1: " + productService.getById(1L));
            System.out.println("id: 1: " + productService.getById(1L));
            System.out.println("id: 1: " + productService.getById(1L));
        };
    }
}
