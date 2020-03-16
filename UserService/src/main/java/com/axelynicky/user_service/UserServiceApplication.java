package com.axelynicky.user_service;

import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Utils.JwtTokenUtility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableJpaAuditing
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);

        Client client = new Client();
        client.setId(Long.parseLong("34523452345"));
        client.setName("hola");
        client.setLastName("hola");
        String token = new JwtTokenUtility().generateToken(client);
        token.length();
    }
}
