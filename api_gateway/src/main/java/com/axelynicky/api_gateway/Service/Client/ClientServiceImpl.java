package com.axelynicky.api_gateway.Service.Client;

import com.axelynicky.api_gateway.Domain.Client;
import com.axelynicky.api_gateway.Domain.RestClientResponse;
import com.axelynicky.api_gateway.Exceptions.NotFoundException;
import com.axelynicky.api_gateway.Network.RestClient;
import com.axelynicky.api_gateway.WebModels.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;

@Component
public class ClientServiceImpl  implements ClientService {

    @Autowired
    RestClient restClient;

    @Override
    public Client getClientForCredentials(UserCredentials credentials) {
        String url = "http://localhost:8081/clients/getFromCredentials";
        try{
            RestClientResponse<Client> response = restClient.post(url,credentials, Client.class);
            if( response.isSuccessful() ) {
                return response.getBody();
            }
        }catch (RestClientException e){
            throw new NotFoundException("Usuario y/o contraseña incorrector");
        }
        throw new NotFoundException("Usuario y/o contraseña incorrector");
    }
}
