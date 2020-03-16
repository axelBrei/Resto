package com.axelynicky.user_service.Domain;

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
@Table(name = "eatables")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Eatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    Float price;

    String type;

    @OneToOne(targetEntity = Promotion.class)
    Promotion promotion;

    @ManyToOne
    Favorite favorite;

    @ManyToOne
    Menu menu;
}
