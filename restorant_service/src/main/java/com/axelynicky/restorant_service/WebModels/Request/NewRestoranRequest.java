package com.axelynicky.restorant_service.WebModels.Request;

import com.axelynicky.restorant_service.Domain.Location;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewRestoranRequest {

    @JsonProperty("restorantName")
    String restorantName;

    @JsonProperty("locations")
    List<Location> locations;
}
