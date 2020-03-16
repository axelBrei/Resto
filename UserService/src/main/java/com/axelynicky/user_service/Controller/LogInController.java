package com.axelynicky.user_service.Controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Utils.JwtTokenUtility;
import com.axelynicky.user_service.Utils.PasswordUtils;
import com.axelynicky.user_service.Utils.WebResponses;
import com.axelynicky.user_service.WebModels.UserCredentials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class LogInController {


    @Autowired
    ClientRepository clientRepository;

    JwtTokenUtility jwtTokenUtility = new JwtTokenUtility();

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody UserCredentials credentials){
        try{
            PasswordUtils passwordUtils = new PasswordUtils();
            Optional<Client> databseResponse =
                    clientRepository.findClientByNameAndPassword(
                        credentials.getUsername(),
                        passwordUtils.encrypt(credentials.getPassword())
                    );
            Client client = databseResponse.get();
            String token = jwtTokenUtility.generateToken(client);
            return WebResponses.ok(token);
        }catch (NoSuchElementException e){
            return WebResponses.notFound(null, "Credenciales invalidas");
        } catch (Exception e){
            e.printStackTrace();
            return WebResponses.badRequest(null, "Ha ocurrido un error al intentar decodificar la contraseña");
        }
    }

    @RequestMapping(value = "/reauthenticate", method = RequestMethod.GET)
    public ResponseEntity reAuthenticate(@RequestHeader(value = "Authorization", required = false) String bearerToken) {

        try {
            String token = bearerToken.substring(7);
            if(!token.startsWith("Bearer ")){
                throw new NullPointerException("");
            }
            if(jwtTokenUtility.isValidToken(token)) {
                Client client = jwtTokenUtility.getAllClaimsFromToken(token);
                return WebResponses.ok(jwtTokenUtility.generateToken(client));
            }else
                return WebResponses.notFound(token, "El token proporcionado es invalido");
        }catch (JWTVerificationException e){
            return WebResponses.badRequest(null, "Error al intentar validar el token");
        }catch (NullPointerException e){
            return WebResponses.badRequest(null, "No se ha proporcionado un metodo de autentificacion");
        }
    }
}
