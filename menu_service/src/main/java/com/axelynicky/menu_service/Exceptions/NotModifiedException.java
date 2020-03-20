package com.axelynicky.menu_service.Exceptions;

import static org.springframework.http.HttpStatus.*;

import org.springframework.web.server.ResponseStatusException;

public class NotModifiedException extends ResponseStatusException {

    public NotModifiedException(String msg) {
        super(NOT_MODIFIED, msg);
    }
}
