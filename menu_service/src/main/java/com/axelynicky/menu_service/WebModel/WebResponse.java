package com.axelynicky.menu_service.WebModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class WebResponse {

    public WebResponse(String message) {
        this.message = message;
    }

    public WebResponse(Object o) {
        this.body = o;
    }

    @NonNull
    String message;

    Object body;
}
