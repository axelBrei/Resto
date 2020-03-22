package com.axelynicky.menu_service.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role {

    Long id;

    @NonNull
    String name;
}
