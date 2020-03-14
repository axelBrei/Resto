package com.axelynicky.resto.Domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@javax.persistence.Table(name = "restorants")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Restorant {
    // TODO add relations

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ElementCollection(targetClass = Location.class)
    @CollectionTable(name = "locations")
    List<Location> locations;

    @ElementCollection(targetClass = Menu.class)
    @CollectionTable(name = "menues")
    List<Menu> menues;

    Float score;

    @ElementCollection(targetClass = Table.class)
    @CollectionTable(name = "tables")
    List<Table> tables;

    @ElementCollection(targetClass = Review.class)
    @CollectionTable(name = "reviews")
    List<Review> reviews;

    @ElementCollection(targetClass = Waiter.class)
    @CollectionTable(name = "waiters")
    List<Waiter> waiters;
}
