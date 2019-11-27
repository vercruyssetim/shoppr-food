package com.switchfully.shoppr;

import com.switchfully.shoppr.food.Food;
import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.recipe.Recipe;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.switchfully.shoppr.food.Food.food;
import static com.switchfully.shoppr.food.FoodType.VEGETABLE;
import static com.switchfully.shoppr.recipe.Ingredient.ingredient;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static com.switchfully.shoppr.recipe.RecipeBuilder.recipe;

@DataJpaTest
class ApplicationTest {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void test() {
        foodRepository.save(food("Tomato", VEGETABLE));
        Iterable<Food> all = foodRepository.findAll();

        System.out.println(all);
    }

    @Test
    void recipemake() {
        Recipe recipe =
                recipe()
                        .ingredients(
                                ingredient(food("Tomato", VEGETABLE), 1, PIECE))
                        .build();

        recipeRepository.save(recipe);
    }
}
