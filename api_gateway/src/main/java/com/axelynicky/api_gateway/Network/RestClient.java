package com.axelynicky.api_gateway.Network;

import com.axelynicky.api_gateway.Domain.QueryParam;
import com.axelynicky.api_gateway.Domain.RestClientResponse;

public interface RestClient {

    <T> RestClientResponse<T> get(String url, Class<T> classType);
    <T> RestClientResponse<T> post(String url, Object body, Class<T> responseClassType);
    <T> RestClientResponse<T> put(String url, Object body, Class<T> responseClassType);
    <T> RestClientResponse<T> delete(String url, Class<T> responseClassType);

    String composeUrl(QueryParam ...params);
    String composeUrl(String baseUrl, QueryParam ...params);
}
