package com.axelynicky.user_service.Controller;

import com.axelynicky.user_service.Service.Client.ClientService;
import com.axelynicky.user_service.WebModels.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/getFromCredentials", method = RequestMethod.POST)
    public ResponseEntity getClientForCredentials(@RequestBody UserCredentials credentials) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        clientService.getClientFromUserCreds(credentials)
                );
    }
}
