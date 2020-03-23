package com.axelynicky.restorant_service.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@javax.persistence.Table(name = "restorants")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@RequiredArgsConstructor
public class Restorant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NonNull
    String name;

    @ManyToMany(targetEntity = Location.class, fetch = FetchType.LAZY)
    List<Location> locations;


    @ElementCollection(targetClass = Long.class)
    List<Long> menues;

    Float score;

//    @ElementCollection(targetClass = Table.class)
//    @CollectionTable(name = "tables")
    @OneToMany(targetEntity = Table.class)
    List<Table> tables;

    @OneToMany(targetEntity = Review.class)
    List<Review> reviews;

    @ElementCollection(targetClass = Waiter.class)
    @CollectionTable(name = "waiters")
    List<Waiter> waiters;
}
