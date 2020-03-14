package com.axelynicky.resto.Domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "countries")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country {
    @Id
    String id;

    String name;

    @ElementCollection(targetClass = State.class)
    @CollectionTable(name = "state")
    List<State> states;
}
