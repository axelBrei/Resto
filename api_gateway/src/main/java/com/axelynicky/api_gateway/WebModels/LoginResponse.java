package com.axelynicky.api_gateway.WebModels;

import com.axelynicky.api_gateway.Domain.Client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    String token;
    Client client;
}
