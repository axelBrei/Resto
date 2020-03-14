package com.axelynicky.resto.Domain;

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

    @OneToOne(targetEntity = Order.class, cascade = CascadeType.ALL)
    Order currentOrder;
}
