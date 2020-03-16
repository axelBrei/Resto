package com.axelynicky.user_service.WebModels;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewUserRequest {

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String lastName;

    @NotNull
    String mail;
}
