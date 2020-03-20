package com.axelynicky.menu_service.Exceptions;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.server.ResponseStatusException;


public class BadRequestException extends ResponseStatusException {

    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }
}
