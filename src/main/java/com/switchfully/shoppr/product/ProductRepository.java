package com.switchfully.shoppr.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);
    List<Product> findByFoodType(ProductType productType);
}
