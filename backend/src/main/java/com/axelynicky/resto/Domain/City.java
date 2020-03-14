package com.axelynicky.resto.Domain;

import org.hibernate.annotations.GenericGenerator;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
