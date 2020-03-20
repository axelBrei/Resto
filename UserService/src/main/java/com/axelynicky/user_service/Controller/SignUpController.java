package com.axelynicky.user_service.Controller;

import com.axelynicky.user_service.Service.SignUp.SignUpService;
import com.axelynicky.user_service.Utils.WebResponses;
import com.axelynicky.user_service.WebModels.NewUserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    SignUpService signUpService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity createNewUser(@RequestBody NewUserRequest body) {
        return WebResponses.ok(
                signUpService.register(body)
        );
    }
}