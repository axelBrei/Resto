package com.axelynicky.api_gateway.Domain;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class RestClientResponse<T> {

    HttpStatus status;
    T body;

    public Boolean isSuccessful() {
        return status.is2xxSuccessful();
    }
}
