package com.axelynicky.restorant_service.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "locations")
public class Location {

    @Id
    Long id;

    String streetName;

    @Transient
    Long restorantId;

    @Transient
    String country;

    @Transient
    String state;

    @Transient
    String city;

    @Transient
    String neighborhood;

    @Transient
    Long latitude;

    @Transient
    Long longitide;

}
