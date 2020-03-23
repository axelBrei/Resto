package com.axelynicky.restorant_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestorantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestorantServiceApplication.class, args);
    }

}
