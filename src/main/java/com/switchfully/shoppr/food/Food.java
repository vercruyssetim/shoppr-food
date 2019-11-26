package com.switchfully.shoppr.food;

import javax.persistence.*;

@Entity
@Table(name = "FOOD")
public class Food {

    @Id
    @SequenceGenerator(name = "food_seq", sequenceName = "food_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    private long id;

    private String name;

    private Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
