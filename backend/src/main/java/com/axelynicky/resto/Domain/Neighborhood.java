package com.axelynicky.resto.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "neighborhoods")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Neighborhood {

    @Id
    String id;

    String name;
}
