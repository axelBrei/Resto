package com.axelynicky.api_gateway.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryParam {

    String key;
    Object value;
}
