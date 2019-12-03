package com.switchfully.shoppr.product;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @SequenceGenerator(name = "food_seq", sequenceName = "food_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    private long id;

    private String name;

    @Enumerated(STRING)
    private ProductType productType;

    @Enumerated(STRING)
    private QuantityType quantityType;

    private Product() {
    }

    private Product(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }

    public static Product product(String name, ProductType productType) {
        return new Product(name, productType);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }
}
