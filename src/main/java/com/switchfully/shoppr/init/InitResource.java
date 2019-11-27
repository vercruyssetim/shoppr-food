package com.switchfully.shoppr.init;

import com.switchfully.shoppr.food.Food;
import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.recipe.Ingredient;
import com.switchfully.shoppr.recipe.Quantity;
import com.switchfully.shoppr.recipe.Recipe;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

import static com.switchfully.shoppr.food.FoodType.VEGETABLE;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static java.util.Arrays.asList;

@RestController
public class InitResource {

    private FoodRepository foodRepository;

    private RecipeRepository recipeRepository;

    public InitResource(FoodRepository foodRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping(path = "init")
    public void init() {
        foodRepository.deleteAll();
        recipeRepository.deleteAll();

        foodRepository.saveAll(
                asList(
                        new Food("Tomato", VEGETABLE)
                )
        );

        Recipe recipe = new Recipe("Een heerlijke tomaat",
                new Ingredient(foodRepository.findByName("Tomato"), new Quantity(1, PIECE))
        );

        recipeRepository.save(recipe);
    }
}
