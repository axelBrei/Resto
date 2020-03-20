package com.axelynicky.user_service.Domain;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(targetEntity = Restorant.class)
    Restorant restorant;

    @OneToOne(targetEntity = Client.class)
    Client client;

    Float totalPrice;

    @ElementCollection(targetClass = Eatable.class)
    @CollectionTable(name = "eatable")
    List<Eatable> eatables;
}
