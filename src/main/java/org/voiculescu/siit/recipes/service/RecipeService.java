package org.voiculescu.siit.recipes.service;

import org.voiculescu.siit.recipes.model.Recipe;
import org.voiculescu.siit.recipes.model.RecipeCategory;

import java.util.List;

public interface RecipeService {

    List<Recipe> findAll();

    Recipe findById(long id);

    void save(Recipe recipe);

    void deleteById(long id);

    List<Recipe> findByCategory(RecipeCategory category);

}
