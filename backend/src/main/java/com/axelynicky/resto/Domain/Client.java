package com.axelynicky.resto.Domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "clients")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String lastName;
    Float fiability;
    Date signUpDate;

    @ElementCollection(targetClass = Favorite.class)
    @CollectionTable(name = "favorite")
    List<Favorite> favorites;

    @ElementCollection(targetClass = Favorite.class)
    @CollectionTable(name = "visit")
    List<Visit> visits;
}
