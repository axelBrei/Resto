package com.axelynicky.resto.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "locations")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    Country country;

    @OneToOne(targetEntity = State.class)
    @JoinColumn(name = "state_id")
    State state;

    @OneToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    City city;

    @OneToOne(targetEntity = Neighborhood.class)
    @JoinColumn(name = "neighborhood_id")
    Neighborhood neighborhood;

    Long latitude;
    Long longitide;

}
