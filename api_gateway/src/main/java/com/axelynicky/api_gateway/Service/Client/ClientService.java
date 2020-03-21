package com.axelynicky.api_gateway.Service.Client;

import com.axelynicky.api_gateway.Domain.Client;
import com.axelynicky.api_gateway.WebModels.UserCredentials;

import java.util.HashMap;

public interface ClientService {
    Client getClientForCredentials(UserCredentials credentials);
}
