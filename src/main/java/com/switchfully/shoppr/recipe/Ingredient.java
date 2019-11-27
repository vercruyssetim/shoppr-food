package com.switchfully.shoppr.recipe;

import com.switchfully.shoppr.food.Food;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "INGREDIENT")
public class Ingredient {

    @Id
    @SequenceGenerator(name = "ingredient_seq", sequenceName = "ingredient_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "ingredient_seq", strategy = SEQUENCE)
    private long id;

    @OneToOne
    @JoinColumn(name = "FK_FOOD_ID")
    private Food food;

    @Embedded
    private Quantity quantity;

    private Ingredient() {
    }

    public Ingredient(Food food, Quantity quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
