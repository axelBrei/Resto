package com.axelynicky.api_gateway.Utils;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ApiError {

    @NonNull
    String path;

    @NonNull
    String message;

    Date timestamp = new Date();
}
