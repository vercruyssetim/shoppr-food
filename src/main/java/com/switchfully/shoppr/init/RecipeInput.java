package com.switchfully.shoppr.init;

import java.util.Arrays;
import java.util.List;

public class RecipeInput {
    private String description;
    private List<IngredientInput> ingredientList;
    private List<String> instructionList;

    public String getDescription() {
        return description;
    }

    public List<IngredientInput> getIngredientList() {
        return ingredientList;
    }

    public List<String> getInstructionList() {
        return instructionList;
    }
}
