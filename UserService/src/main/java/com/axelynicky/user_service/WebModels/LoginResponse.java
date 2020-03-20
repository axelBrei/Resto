package com.axelynicky.user_service.WebModels;

import com.axelynicky.user_service.Domain.Client;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    String userToken;
    Client client;
}
