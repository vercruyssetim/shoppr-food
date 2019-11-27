package com.switchfully.shoppr;

import com.switchfully.shoppr.food.Food;
import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.recipe.Ingredient;
import com.switchfully.shoppr.recipe.Quantity;
import com.switchfully.shoppr.recipe.Recipe;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.switchfully.shoppr.food.FoodType.VEGETABLE;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static java.util.Arrays.asList;

@DataJpaTest
class ApplicationTest {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void test() {
        foodRepository.save(new Food("Tomato", VEGETABLE));
        Iterable<Food> all = foodRepository.findAll();

        System.out.println(all);
    }

    @Test
    void recipe() {
        Recipe recipe = new Recipe("Een heerlijke tomaat",
                        new Ingredient(new Food("Tomato", VEGETABLE), new Quantity(1, PIECE))
                );

        recipeRepository.save(recipe);
    }
}
