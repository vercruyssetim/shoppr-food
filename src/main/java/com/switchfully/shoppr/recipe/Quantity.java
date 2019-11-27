package com.switchfully.shoppr.recipe;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {

    private int amount;

    private QuantityType quantityType;

    private Quantity() {
    }

    public Quantity(int amount, QuantityType quantityType) {
        this.amount = amount;
        this.quantityType = quantityType;
    }

    public int getAmount() {
        return amount;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }
}
