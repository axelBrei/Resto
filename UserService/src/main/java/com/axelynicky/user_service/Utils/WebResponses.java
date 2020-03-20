package com.axelynicky.user_service.Utils;


import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import lombok.Data;

import static org.springframework.http.HttpStatus.*;

public class WebResponses {

    public static ResponseEntity ok(Object body) {
        Response response = new Response("Ok", body);
        return new ResponseEntity(response, OK);
    }

    public static ResponseEntity badRequest(@Nullable Object body, @Nullable String message) {
        Response response = new Response(
                message != null ? message : "Bad Request",
                body
        );
        return new ResponseEntity(response, BAD_REQUEST);
    }

    public static ResponseEntity notFound(@Nullable Object body, @Nullable String message) {
        Response response = new Response(
                message != null ? message : "Not found",
                body
        );
        return new ResponseEntity(response, NOT_FOUND);
    }

    public static ResponseEntity created(@Nullable Object body, @Nullable String message) {
        Response response = new Response(
                message != null ? message : "Creado",
                body
        );
        return new ResponseEntity(response, CREATED);
    }

    @Data
    private static class Response {
        public Response(String message, @Nullable Object body) {
            this.message = message;
            this.body = body;
        }

        String message;
        Object body;
    }
}
