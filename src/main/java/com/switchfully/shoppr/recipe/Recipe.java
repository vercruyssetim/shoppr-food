package com.switchfully.shoppr.recipe;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "RECIPE")
public class Recipe {

    @Id
    @SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "recipe_seq", strategy = SEQUENCE)
    private long id;

    private String description;

    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "FK_RECIPE_ID")
    private List<Ingredient> ingredients;

    private Recipe() {

    }

    public Recipe(String description, List<Ingredient> ingredients) {
        this.description = description;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
