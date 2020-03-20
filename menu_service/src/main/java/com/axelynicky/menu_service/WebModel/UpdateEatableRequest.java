package com.axelynicky.menu_service.WebModel;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEatableRequest {

    @NonNull
    Long menuId;
    @NonNull
    Long eatableId;

    String name;
    Float price;
}
