package com.switchfully.shoppr.init;

import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.food.FoodType;
import com.switchfully.shoppr.recipe.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.switchfully.shoppr.food.Food.food;
import static com.switchfully.shoppr.food.FoodType.*;
import static com.switchfully.shoppr.recipe.Ingredient.ingredient;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static com.switchfully.shoppr.recipe.RecipeBuilder.recipe;
import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.asList;

@RestController
public class InitResource {

    private FoodRepository foodRepository;
    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    public InitResource(FoodRepository foodRepository, IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping(path = "init")
    public void init() throws IOException {
        recipeRepository.deleteAll();
        ingredientRepository.deleteAll();
        foodRepository.deleteAll();

        loadFood("Vegetables.csv", VEGETABLE);
        loadFood("Spices.csv", SPICE);
        loadFood("Fruits.csv", FRUIT);


        recipeRepository.saveAll(
                asList(
                        recipe()
                                .description("Een heerlijke tomaat")
                                .ingredients(
                                        ingredient(foodRepository.findByName("Tomato"), 1, PIECE)
                                )
                                .build()
                ));
    }

    private void loadFood(String fileName, FoodType foodType) throws IOException {
        readAllLines(new ClassPathResource(fileName).getFile().toPath())
                .stream()
                .map(v -> food(v, foodType))
                .forEach(v -> foodRepository.save(v));
    }
}
