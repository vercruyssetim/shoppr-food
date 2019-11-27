package com.switchfully.shoppr.init;

import java.util.List;

public class RecipeInput {
    private String description;
    private List<IngredientInput> ingredientList;

    public String getDescription() {
        return description;
    }

    public List<IngredientInput> getIngredientList() {
        return ingredientList;
    }
}
