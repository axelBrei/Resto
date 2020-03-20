package com.axelynicky.user_service.Controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Repository.ClientRepository;
import com.axelynicky.user_service.Service.Login.LoginService;
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
    LoginService loginService;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity signIn(@RequestBody UserCredentials credentials) {
        return WebResponses.ok(
                loginService.login(credentials)
        );
    }

    @RequestMapping(value = "/reauthenticate", method = RequestMethod.GET)
    public ResponseEntity reAuthenticate(@RequestHeader(value = "Authorization") String bearerToken) {

        return WebResponses.ok(
                loginService.reAuthenticate(bearerToken)
        );
    }
}
