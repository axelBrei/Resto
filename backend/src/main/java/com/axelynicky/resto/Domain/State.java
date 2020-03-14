package com.axelynicky.resto.Domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
