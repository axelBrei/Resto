package com.axelynicky.user_service.Service.SignUp;

import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Exceptions.BadRequestException;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Utils.PasswordUtils;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.NewUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    ClientRepository clientRepository;


    @Override
    public Client register(NewUserRequest request) throws BadRequestException {
        PasswordUtils pu;
        try {
            pu = new PasswordUtils();
        } catch (Exception e) {
            throw new BadRequestException("Error al crear usuario");
        }
        String encriptedPass = pu.encrypt(request.getPassword());
        Client client = new Client(
                request.getUsername(),
                request.getLastName(),
                encriptedPass,
                request.getMail()
        );
        client.setSignUpDate(new Date());
        client.setFiability(0.0f);
        client.setPassword(encriptedPass);
        client = clientRepository.registerNewClient(client);
        return client;
    }
}
