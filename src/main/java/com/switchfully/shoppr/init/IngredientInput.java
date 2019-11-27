package com.switchfully.shoppr.init;

import com.switchfully.shoppr.recipe.QuantityType;

public class IngredientInput {

    private String name;
    private int amount;
    private QuantityType type;

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public QuantityType getType() {
        return type;
    }
}
