package com.switchfully.shoppr.food;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FoodRepository extends CrudRepository<Food, Long> {

    Food findByName(String name);
    List<Food> findByFoodType(FoodType foodType);
}
