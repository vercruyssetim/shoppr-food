package com.switchfully.shoppr.food;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "FOOD")
public class Food {

    @Id
    @SequenceGenerator(name = "food_seq", sequenceName = "food_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    private long id;

    private String name;

    @Enumerated(STRING)
    private FoodType foodType;

    private Food() {
    }

    private Food(String name, FoodType foodType) {
        this.name = name;
        this.foodType = foodType;
    }

    public static Food food(String name, FoodType foodType) {
        return new Food(name, foodType);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FoodType getFoodType() {
        return foodType;
    }
}
