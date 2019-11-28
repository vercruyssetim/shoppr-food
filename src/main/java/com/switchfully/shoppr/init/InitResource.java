package com.switchfully.shoppr.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.switchfully.shoppr.food.FoodRepository;
import com.switchfully.shoppr.food.FoodType;
import com.switchfully.shoppr.recipe.Ingredient;
import com.switchfully.shoppr.recipe.Instruction;
import com.switchfully.shoppr.recipe.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.switchfully.shoppr.food.Food.food;
import static com.switchfully.shoppr.recipe.Ingredient.ingredient;
import static com.switchfully.shoppr.recipe.RecipeBuilder.recipe;
import static java.util.stream.Collectors.toList;

@RestController
public class InitResource {

    private static Logger LOGGER = LoggerFactory.getLogger(InitResource.class);

    private FoodRepository foodRepository;
    private RecipeRepository recipeRepository;

    public InitResource(FoodRepository foodRepository, RecipeRepository recipeRepository) {
        this.foodRepository = foodRepository;
        this.recipeRepository = recipeRepository;
    }

    @GetMapping(path = "init")
    public void init() throws IOException {
        recipeRepository.deleteAll();
        foodRepository.deleteAll();

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(getClass().getClassLoader());

        Stream.of(resolver.getResources("classpath:food/*"))
                .peek(r -> LOGGER.info("Loading " + r.getFilename()))
                .forEach(r -> loadFood(r, FoodType.valueOf(r.getFilename())));


        Stream.of(resolver.getResources("classpath:recipe/*.json"))
                .peek(r -> LOGGER.info("Loading " + r.getFilename()))
                .map(this::toRecipe)
                .map(r -> recipe()
                        .description(r.getDescription())
                        .ingredients(toIngredientList(r))
                        .instructions(toInstruction(r))
                        .build())
                .forEach(recipe -> recipeRepository.save(recipe));

        LOGGER.info("initialisation succeeded!!");
    }

    private RecipeInput toRecipe(Resource resource) {
        try {
            return new ObjectMapper().readValue(resource.getInputStream(), RecipeInput.class);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private List<Instruction> toInstruction(RecipeInput recipeInput) {
        return recipeInput.getInstructionList().stream().map(this::toInstruction).collect(toList());
    }

    private Instruction toInstruction(String instruction) {
        return new Instruction(instruction);
    }

    private List<Ingredient> toIngredientList(RecipeInput recipeInput) {
        return recipeInput.getIngredientList().stream().map(this::toIngredient).collect(toList());
    }

    private Ingredient toIngredient(IngredientInput ingredientInput) {
        return ingredient(foodRepository.findByName(ingredientInput.getName()), ingredientInput.getAmount(), ingredientInput.getType());
    }

    private void loadFood(Resource resource, FoodType foodType) {
        try (InputStream inputStream = resource.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            reader
                    .lines()
                    .map(v -> food(v, foodType))
                    .forEach(v -> foodRepository.save(v));
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
