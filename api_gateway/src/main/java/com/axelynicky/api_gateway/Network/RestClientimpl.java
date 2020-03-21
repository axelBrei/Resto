package com.axelynicky.api_gateway.Network;

import com.axelynicky.api_gateway.Domain.QueryParam;
import com.axelynicky.api_gateway.Domain.RestClientResponse;
import com.axelynicky.api_gateway.Utils.JwtTokenUtility;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import javax.xml.ws.Response;

@Component
public class RestClientimpl  implements RestClient{
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER = "Bearer ";

    @Autowired
    RestTemplate restTemplate;

    String serverAddres = "http://localhost:8080/";
    private HttpHeaders headers;
    private Gson gson;
    private JwtTokenUtility tokenUtility;

    @Autowired
    private void addHeaders() {
        headers = new HttpHeaders();
        tokenUtility = new JwtTokenUtility();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add(AUTHORIZATION_HEADER, BEARER + tokenUtility.createAutorizationToken());
    }
    @Autowired
    public void init() {
        gson = new Gson();
    }


    @Override
    public <T> RestClientResponse<T> get(String url, Class<T> classType) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        RestClientResponse<T> response = new RestClientResponse<>();
        try {
            ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.GET,requestEntity, classType);

            response.setStatus(responseEntity.getStatusCode());
            response.setBody(responseEntity.getBody());
            return response;
        }catch (RestClientException e){
            e.printStackTrace();
            return  null;
        }
    }

    @Override
    public <T> RestClientResponse<T> post(String url, Object body, Class<T> responseClassType) {
        RestClientResponse<T> response = new RestClientResponse<>();
       try {
           HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(body), headers);
           ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST,requestEntity, responseClassType);
           response.setStatus(responseEntity.getStatusCode());
           response.setBody(responseEntity.getBody());
       }catch (HttpClientErrorException e){
           response.setStatus(e.getStatusCode());
       }
        return response;
    }

    @Override
    public <T> RestClientResponse<T> put(String url, Object body, Class<T> responseClassType) {
        HttpEntity<String> requestEntity = new HttpEntity<>(gson.toJson(body), headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity, responseClassType);
        RestClientResponse<T> response = new RestClientResponse<>();
        response.setStatus(responseEntity.getStatusCode());
        response.setBody(responseEntity.getBody());
        return response;
    }

    @Override
    public <T> RestClientResponse<T> delete(String url, Class<T> responseClassType) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE,requestEntity, responseClassType);
        RestClientResponse<T> response = new RestClientResponse<>();
        response.setStatus(responseEntity.getStatusCode());
        response.setBody(responseEntity.getBody());
        return response;
    }

    @Override
    public String composeUrl(QueryParam... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(serverAddres);
        Arrays.asList(params).forEach( param -> {
            builder.queryParam(param.getKey(), param.getValue());
        });
        return builder.build().toUriString();
    }

    @Override
    public String composeUrl(String baseUrl, QueryParam... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl);
        Arrays.asList(params).forEach( param -> {
            builder.queryParam(param.getKey(), param.getValue());
        });
        return builder.build().toUriString();
    }
}
