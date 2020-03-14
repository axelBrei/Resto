package com.axelynicky.resto.Service;

import com.google.gson.Gson;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpService {
    public static MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(5))
            .build();
    private static Gson gson = new Gson();

    private HttpService() {
    }

    public static OkHttpClient getInstance() {
        return client;
    }

    private static Request.Builder buildRequest(String url) {
        return new Request.Builder()
                .url(url);
    }

    public static <T> T get(String url, Class<T> objectClass)  throws IOException{
        Request request = buildRequest(url).get().build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String respString = response.body().string();
            return gson.fromJson(respString, objectClass);
        }
        return null;
    }

    public static <T> void asyncGet(String uri, Callback callback) {
        Request request = buildRequest(uri).get().build();
        client.newCall(request).enqueue(callback);
    }
}
