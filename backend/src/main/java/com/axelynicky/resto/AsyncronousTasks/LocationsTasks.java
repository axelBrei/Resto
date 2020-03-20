package com.axelynicky.resto.AsyncronousTasks;

import com.axelynicky.resto.Domain.City;
import com.axelynicky.resto.Domain.Neighborhood;
import com.axelynicky.resto.Domain.State;
import com.axelynicky.resto.Repository.CitiesRepository;
import com.axelynicky.resto.Repository.StatesRepository;
import com.axelynicky.resto.Service.HttpService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Response;


public class LocationsTasks {

    private ObjectMapper mapper = new ObjectMapper();

    public List<City> fetchCitiesFromML(String stateId) {
        try {
            HashMap resp = HttpService.get("https://api.mercadolibre.com/classified_locations/states/" + stateId, HashMap.class);
            return mapper.convertValue(resp.get("cities"), new TypeReference<List<City>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

    }

    public List<Neighborhood> fetchHoodsFromML(String cityId) {
        try {
            HashMap response = HttpService.get("https://api.mercadolibre.com/classified_locations/cities/" + cityId, HashMap.class);
            return mapper.convertValue(response.get("neighborhoods"), new TypeReference<List<Neighborhood>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }
}
