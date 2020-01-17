package com.spring.RecipeProject.services;

import com.spring.RecipeProject.domain.Recipe;
import com.spring.RecipeProject.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {
    RecipeService recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipeById(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull(recipeReturned,"Null recipe returned");
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }

    @Test
    void getRecipes() {
        Recipe recipe = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(recipe);
        when(recipeService.getRecipes()).thenReturn(recipes);

        Set<Recipe> recipeSet = recipeService.getRecipes();

        assertThat(recipeSet.size()).isEqualTo(1);
        verify(recipeRepository,times(1)).findAll();
        verify(recipeRepository,never()).findById(anyLong());
    }
}