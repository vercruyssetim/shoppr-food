package com.switchfully.shoppr.recipe;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity {

    private long amount;

    private QuantityType quantityType;

    private Quantity() {
    }

    public Quantity(long amount, QuantityType quantityType) {
        this.amount = amount;
        this.quantityType = quantityType;
    }

    public long getAmount() {
        return amount;
    }

    public QuantityType getQuantityType() {
        return quantityType;
    }
}
