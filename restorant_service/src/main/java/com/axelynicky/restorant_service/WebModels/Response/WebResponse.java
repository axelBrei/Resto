package com.axelynicky.restorant_service.WebModels.Response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebResponse {

    String message;
    Object body;

    public WebResponse(Object body) {
        this.body = body;
    }

    public WebResponse(String msg) {
        this.message = msg;
    }
}
