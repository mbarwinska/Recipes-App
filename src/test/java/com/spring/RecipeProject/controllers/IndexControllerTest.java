package com.spring.RecipeProject.controllers;

import com.spring.RecipeProject.domain.Recipe;
import com.spring.RecipeProject.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    private IndexController indexController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);

    }

    @Test
    void testMockMVC () throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipes.add(recipe1);
        recipes.add(recipe2);
        String expectedViewName = "index";

        when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);
        String viewName = indexController.getIndexPage(model);

        assertThat(viewName).isEqualTo(expectedViewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertThat(setInController.size()).isEqualTo(2);
    }
}