package com.axelynicky.resto.Controller;

import com.axelynicky.resto.AsyncronousTasks.LocationsTasks;
import com.axelynicky.resto.Domain.City;
import com.axelynicky.resto.Domain.Neighborhood;
import com.axelynicky.resto.Domain.State;
import com.axelynicky.resto.Repository.CitiesRepository;
import com.axelynicky.resto.Repository.StatesRepository;
import com.axelynicky.resto.Service.HttpService;
import com.axelynicky.resto.WebModel.ListContainer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("poblate")
public class LocationsController {

    @Autowired
    private StatesRepository statesRepository;
    @Autowired
    private CitiesRepository citiesRepository;

    LocationsTasks locationsTasks = new LocationsTasks();

    private ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "states", method = RequestMethod.GET)
    public ResponseEntity poblateStates() {
        try {
            HashMap<String, Object> container = HttpService.get("https://api.mercadolibre.com/classified_locations/countries/AR", HashMap.class);
            List<State> list = mapper.convertValue(container.get("states"), new TypeReference<List<State>>() {
            });
            statesRepository.saveAll(list);
            return ResponseEntity.ok(new ListContainer<>(list));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(method = RequestMethod.GET, value = "cities")
    public ResponseEntity getCitiesForState(@RequestParam(value = "stateId") String stateId) {
        Optional<State> optionalState = statesRepository.findById(stateId);
        State state = optionalState
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (state != null && state.getCities().isEmpty()) {
            List<City> cities = locationsTasks.fetchCitiesFromML(stateId);
            state.setCities(cities);
            ((Runnable) () -> statesRepository.saveAndFlush(state)).run();
        }
        return ResponseEntity.ok(state.getCities());
    }

    @RequestMapping(value = "neighborhoods", method = RequestMethod.GET)
    public ResponseEntity getHoodsForCity(@RequestParam("cityId") String cityId) {
        Optional<City> cityOptional = citiesRepository.findById(cityId);
        City city = cityOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (city.getNeighborhoods().isEmpty()) {
            List<Neighborhood> neighborhoods = locationsTasks.fetchHoodsFromML(cityId);
            city.setNeighborhoods(neighborhoods);
            ((Runnable) () -> citiesRepository.save(city)).run();
        }

        return ResponseEntity.ok(city.getNeighborhoods());
    }
}
