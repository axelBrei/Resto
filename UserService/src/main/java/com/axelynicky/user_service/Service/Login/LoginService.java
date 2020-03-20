package com.axelynicky.user_service.Service.Login;

import com.axelynicky.user_service.Exceptions.BadRequestException;
import com.axelynicky.user_service.Exceptions.NotFoundExeption;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.UserCredentials;

public interface LoginService {
    LoginResponse login(UserCredentials credentials) throws NotFoundExeption;

    String reAuthenticate(String token) throws NotFoundExeption, BadRequestException;
}
