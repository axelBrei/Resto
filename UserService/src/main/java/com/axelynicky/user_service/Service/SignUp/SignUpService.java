package com.axelynicky.user_service.Service.SignUp;

import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Exceptions.BadRequestException;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.NewUserRequest;

public interface SignUpService {
    Client register(NewUserRequest request) throws BadRequestException;
}
