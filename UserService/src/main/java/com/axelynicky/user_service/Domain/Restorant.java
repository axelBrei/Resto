package com.axelynicky.user_service.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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


    @OneToMany(cascade = CascadeType.ALL)
    List<Location> locations;

    @OneToMany(cascade = CascadeType.ALL)
    List<Menu> menues;

    Float score;

    @OneToMany(cascade = CascadeType.ALL)
    List<Table> tables;

    @OneToMany(cascade = CascadeType.ALL)
    List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL)
    List<Waiter> waiters;
}
