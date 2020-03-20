package com.axelynicky.menu_service.Domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@Table(name = "eatables")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Eatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    String name;

    @NonNull
    Float price;

    @NonNull
    String type;

    Long promotionId;

    @Column(name = "menu_id")
    Long menuId;

    public Boolean updatePriceIfChange(Float price) {
        if (!this.price.equals(price)) {
            setPrice(price);
            return true;
        }
        return false;
    }

    public Boolean updateNameIfChange(String name) {
        if (name != null && !this.name.equals(name)) {
            setName(name);
            return true;
        }
        return false;
    }
}
