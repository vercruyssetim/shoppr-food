package com.switchfully.shoppr.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.food.FoodType;
import com.switchfully.shoppr.recipe.Ingredient;
import com.switchfully.shoppr.recipe.IngredientRepository;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;

import static com.switchfully.shoppr.food.Food.food;
import static com.switchfully.shoppr.food.FoodType.*;
import static com.switchfully.shoppr.recipe.Ingredient.ingredient;
import static com.switchfully.shoppr.recipe.QuantityType.PIECE;
import static com.switchfully.shoppr.recipe.RecipeBuilder.recipe;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

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

        List<RecipeInput> recipeInputs = asList(new ObjectMapper().readValue(new ClassPathResource("classpath:recipes.json").getInputStream(), RecipeInput[].class));

        recipeInputs.stream()
                .map(r -> recipe()
                        .description(r.getDescription())
                        .ingredients(toIngredientList(r))
                        .build())
                .forEach(recipe -> recipeRepository.save(recipe));

        LOGGER.info("initialisation succeeded!!");
    }

    private List<Ingredient> toIngredientList(RecipeInput recipeInput) {
        return recipeInput.getIngredientList().stream().map(this::toIngredient).collect(toList());
    }

    private Ingredient toIngredient(IngredientInput ingredientInput) {
        return ingredient(foodRepository.findByName(ingredientInput.getName()), ingredientInput.getAmount(), ingredientInput.getType());
    }

    private void loadFood(String fileName, FoodType foodType) throws IOException {
        InputStream resource;
        try {
            resource = new ClassPathResource(fileName).getInputStream();
        } catch (FileNotFoundException fileNotFoundException) {
            resource = new ClassPathResource(fileName.replace("classpath:", "")).getInputStream();
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
            reader
                    .lines()
                    .map(v -> food(v, foodType))
                    .forEach(v -> foodRepository.save(v));
        }
        resource.close();
    }
}
