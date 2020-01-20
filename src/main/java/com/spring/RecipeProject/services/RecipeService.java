package com.spring.RecipeProject.services;

import com.spring.RecipeProject.command.RecipeCommand;
import com.spring.RecipeProject.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand (RecipeCommand recipeCommand);
}
