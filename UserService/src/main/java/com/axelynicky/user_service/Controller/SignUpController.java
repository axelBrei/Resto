package com.axelynicky.user_service.Controller;

import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Utils.JwtTokenUtility;
import com.axelynicky.user_service.Utils.PasswordUtils;
import com.axelynicky.user_service.Utils.WebResponses;
import com.axelynicky.user_service.WebModels.NewUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    ClientRepository clientRepository;

    JwtTokenUtility tokenUtility = new JwtTokenUtility();


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity createNewUser(@RequestBody NewUserRequest body) {
        try {
            PasswordUtils pu = new PasswordUtils();
            String encriptedPass = pu.encrypt(body.getPassword());
            Client client = new Client(
                    body.getUsername(),
                    body.getLastName(),
                    encriptedPass,
                    body.getMail()
            );
            client.setSignUpDate(new Date());
            client.setFiability(0.0f);
            clientRepository.save(client);

            return WebResponses.created(tokenUtility.generateToken(client), "Cliente creado con exito");
        } catch (Exception e) {
            e.printStackTrace();
            return WebResponses.badRequest(null, "Error al crear un usuario");
        }
    }
}
