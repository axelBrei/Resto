package com.axelynicky.user_service.Service.Login;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Exceptions.BadRequestException;
import com.axelynicky.user_service.Exceptions.NotFoundExeption;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Utils.JwtTokenUtility;
import com.axelynicky.user_service.Utils.PasswordUtils;
import com.axelynicky.user_service.Utils.WebResponses;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginServiceImpl implements LoginService {

    @Autowired
    ClientRepository clientRepository;

    JwtTokenUtility tokenUtility;

    @Autowired
    public void startUtil() {
        tokenUtility = new JwtTokenUtility();
    }

    @Override
    public LoginResponse login(UserCredentials credentials) throws NotFoundExeption {
        PasswordUtils passwordUtils;
        try {
            passwordUtils = new PasswordUtils();
        } catch (Exception e) {
            throw new BadRequestException("Ha ocurrido un error al intentar desencriptar la contraseña");
        }
        Optional<Client> databseResponse =
                clientRepository.findClientByNameAndPassword(
                        credentials.getUsername(),
                        passwordUtils.encrypt(credentials.getPassword())
                );
        Client client = databseResponse
                .orElseThrow(() -> new NotFoundExeption("Credenciales invalidas"));

        String token = tokenUtility.generateToken(client);

        return new LoginResponse(token, client);
    }

    @Override
    public String reAuthenticate(String bearerToken) throws NotFoundExeption, BadRequestException {
        String token = bearerToken.substring(7);
        if (!token.startsWith("Bearer ")) {
            throw new BadRequestException("Tipo de autorizacion invalido, se espera del tipo Bearer");
        }
        try {
            if (tokenUtility.isValidToken(token)) {
                Client client = tokenUtility.getAllClaimsFromToken(token);
                if (clientRepository.existsById(client.getId())) {
                    return tokenUtility.generateToken(client);
                } else
                    throw new NotFoundExeption("El cliente proporcionado con el token no existe");
            } else {
                throw new BadRequestException("Token invalido");
            }
        } catch (JWTVerificationException e) {
            throw new BadRequestException("Token invalido");
        }
    }
}
