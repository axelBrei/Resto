package com.axelynicky.user_service.WebModels;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewUserRequest {

    @NotNull
    @JsonProperty("username")
    String username;

    @NotNull
    @JsonProperty("password")
    String password;

    @NotNull
    @JsonProperty("lastName")
    String lastName;

    @NotNull
    @JsonProperty("mail")
    String mail;
}
