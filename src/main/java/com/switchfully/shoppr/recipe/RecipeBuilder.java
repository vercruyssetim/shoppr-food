package com.switchfully.shoppr.recipe;

import java.util.Arrays;
import java.util.List;

public class RecipeBuilder {

    private String description;
    private List<Ingredient> ingredients;
    private List<Instruction> instructions;

    private RecipeBuilder() {

    }

    public static RecipeBuilder recipe() {
        return new RecipeBuilder();
    }

    public Recipe build() {
        return new Recipe(description, ingredients, instructions);
    }

    public RecipeBuilder description(String description) {
        this.description = description;
        return this;
    }

    public RecipeBuilder ingredients(Ingredient... ingredients) {
        this.ingredients = Arrays.asList(ingredients);
        return this;
    }

    public RecipeBuilder ingredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder instructions(List<Instruction> instructions) {
        this.instructions = instructions;
        return this;
    }
}
