package com.axelynicky.api_gateway.Controller;

import com.axelynicky.api_gateway.Domain.Client;
import com.axelynicky.api_gateway.Service.Client.ClientService;
import com.axelynicky.api_gateway.Utils.JwtTokenUtility;
import com.axelynicky.api_gateway.WebModels.LoginResponse;
import com.axelynicky.api_gateway.WebModels.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class LoginController {

    @Autowired
    ClientService clientService;

    JwtTokenUtility tokenUtility = new JwtTokenUtility();

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UserCredentials userCredentials) {
        Client client = clientService.getClientForCredentials(userCredentials);
        String token = tokenUtility.generateToken(client);
        return ResponseEntity.ok(
                new LoginResponse(token, client)
        );
    }
}
