package com.axelynicky.restorant_service.Domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@javax.persistence.Table(name = "tables")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long currentOrderId;
}
