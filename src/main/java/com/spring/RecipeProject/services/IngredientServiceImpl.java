package com.spring.RecipeProject.services;

import com.spring.RecipeProject.command.IngredientCommand;
import com.spring.RecipeProject.converters.IngredientToIngredientCommand;
import com.spring.RecipeProject.domain.Recipe;
import com.spring.RecipeProject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {

    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeFromDB = recipeRepository.findById(recipeId);

        if (!recipeFromDB.isPresent()){
            throw new RuntimeException("No recipe of this ID!");
        }

        Optional<IngredientCommand> ingredientCommandOptional = recipeFromDB.get().getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();

        if (!ingredientCommandOptional.isPresent()){
            throw  new RuntimeException("No ingredient of this ID!");
        }

        return ingredientCommandOptional.get();
    }
}
