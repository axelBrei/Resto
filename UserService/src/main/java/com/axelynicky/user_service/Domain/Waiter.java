package com.axelynicky.user_service.Domain;


import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@javax.persistence.Table(name = "waiters")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Waiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany
    List<Table> tables;

    Float score;

    @ManyToOne
    @JoinColumn
    Restorant restorant;
}
