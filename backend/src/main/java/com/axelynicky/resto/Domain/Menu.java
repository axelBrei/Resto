package com.axelynicky.resto.Domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "menues")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String type;

    @ElementCollection(targetClass = Eatable.class)
    @CollectionTable(name = "eatables")
    List<Eatable> eatables;

    @ManyToOne
    @JoinColumn
    Restorant restorant;
}
