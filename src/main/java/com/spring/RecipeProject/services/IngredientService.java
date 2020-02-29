package com.spring.RecipeProject.services;

import com.spring.RecipeProject.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
