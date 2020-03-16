package com.axelynicky.user_service.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Table(name = "states")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class State {

    @Id
    @Column(name = "state_id")
    String id;

    String name;

    @OneToMany(cascade = CascadeType.ALL)
    List<City> cities;
}
