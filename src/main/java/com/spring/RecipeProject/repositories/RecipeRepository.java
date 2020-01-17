package com.spring.RecipeProject.repositories;

import com.spring.RecipeProject.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
