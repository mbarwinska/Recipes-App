package com.spring.RecipeProject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    private Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        category.setId(4L);
        assertThat(category.getId()).isEqualTo(4L);
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}