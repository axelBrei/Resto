package com.axelynicky.user_service.WebModels;

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
