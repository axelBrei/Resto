package com.axelynicky.user_service.WebModels;

import lombok.Data;

@Data
public class DatabaseInsert<T> {

    int affectedRows;
    T rsponse;
}
