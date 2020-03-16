package com.axelynicky.user_service.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "cities")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City {

    @Id
    String id;

    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<Neighborhood> neighborhoods;
}
