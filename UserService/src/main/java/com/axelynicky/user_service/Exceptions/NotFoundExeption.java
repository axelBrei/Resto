package com.axelynicky.user_service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundExeption extends ResponseStatusException {

    public NotFoundExeption(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
