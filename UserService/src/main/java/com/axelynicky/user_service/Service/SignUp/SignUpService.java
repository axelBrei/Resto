package com.axelynicky.user_service.Service.SignUp;

import com.axelynicky.user_service.Exceptions.BadRequestException;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.NewUserRequest;

public interface SignUpService {
    LoginResponse register(NewUserRequest request) throws BadRequestException;
}
