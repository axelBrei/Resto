package com.axelynicky.user_service;

import com.axelynicky.user_service.Security.JwtTokenUtility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
        String token = new JwtTokenUtility().createAutorizationToken();
        token.length();
    }
}
