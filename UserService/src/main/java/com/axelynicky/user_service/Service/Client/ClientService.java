package com.axelynicky.user_service.Service.Client;

import com.axelynicky.user_service.Domain.Client;
import com.axelynicky.user_service.Exceptions.NotFoundExeption;
import com.axelynicky.user_service.WebModels.LoginResponse;
import com.axelynicky.user_service.WebModels.UserCredentials;

public interface ClientService {
    Client getClientFromUserCreds(UserCredentials userCredentials) throws NotFoundExeption;
}
