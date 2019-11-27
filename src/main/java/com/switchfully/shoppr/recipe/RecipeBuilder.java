package com.switchfully.shoppr.recipe;

import java.util.Arrays;
import java.util.List;

public class RecipeBuilder {

    private String description;
    private List<Ingredient> ingredients;

    private RecipeBuilder() {

    }

    public static RecipeBuilder recipe() {
        return new RecipeBuilder();
    }

    public Recipe build() {
        return new Recipe(description, ingredients);
    }

    public RecipeBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RecipeBuilder ingredients(Ingredient... ingredients) {
        this.ingredients = Arrays.asList(ingredients);
        return this;
    }
}
