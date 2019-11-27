package com.switchfully.shoppr.init;

import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.food.FoodType;
import com.switchfully.shoppr.recipe.IngredientRepository;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.switchfully.shoppr.food.Food.food;
import static com.switchfully.shoppr.food.FoodType.*;
import static com.switchfully.shoppr.recipe.Ingredient.ingredient;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static com.switchfully.shoppr.recipe.RecipeBuilder.recipe;
import static java.util.Arrays.asList;

@RestController
public class InitResource {

    private static Logger LOGGER = LoggerFactory.getLogger(InitResource.class);

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

        loadFood("classpath:Vegetables.csv", VEGETABLE);
        loadFood("classpath:Spices.csv", SPICE);
        loadFood("classpath:Fruits.csv", FRUIT);
        loadFood("classpath:Dairies.csv", DAIRY);
        loadFood("classpath:Meats.csv", MEAT);
        loadFood("classpath:Oils.csv", OIL);
        loadFood("classpath:Carbs.csv", CARB);


        recipeRepository.saveAll(
                asList(
                        recipe()
                                .description("Een heerlijke tomaat")
                                .ingredients(
                                        ingredient(foodRepository.findByName("Tomato"), 1, PIECE)
                                )
                                .build()
                ));

        LOGGER.info("initialisation succeeded!!");
    }

    private void loadFood(String fileName, FoodType foodType) throws IOException {
        try (InputStream resource = new ClassPathResource(fileName).getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            reader
                    .lines()
                    .map(v -> food(v, foodType))
                    .forEach(v -> foodRepository.save(v));
        }
    }
}
