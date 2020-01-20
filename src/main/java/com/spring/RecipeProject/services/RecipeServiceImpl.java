package com.spring.RecipeProject.services;

import com.spring.RecipeProject.command.RecipeCommand;
import com.spring.RecipeProject.converters.RecipeCommandToRecipe;
import com.spring.RecipeProject.converters.RecipeToRecipeCommand;
import com.spring.RecipeProject.domain.Recipe;
import com.spring.RecipeProject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;
    private RecipeToRecipeCommand recipeToRecipeCommand;
    private RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipe -> recipes.add(recipe));
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (!optionalRecipe.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }
        return optionalRecipe.get();
    }

    @Transactional
    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        RecipeCommand convertedRecipeCommand = recipeToRecipeCommand.convert(savedRecipe);
        return convertedRecipeCommand;
    }
}
