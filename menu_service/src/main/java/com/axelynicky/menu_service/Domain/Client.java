package com.axelynicky.menu_service.Domain;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@NoArgsConstructor
public class Client {
    Long id;

    @NonNull
    String name;
    @NonNull
    String lastName;
    @NonNull
    String password;
    @NonNull
    String mail;

    Role role;

    Float fiability;
    Date signUpDate;
}
