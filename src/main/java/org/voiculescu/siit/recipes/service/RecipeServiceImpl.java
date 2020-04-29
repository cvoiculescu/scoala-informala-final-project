package org.voiculescu.siit.recipes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.voiculescu.siit.recipes.dao.RecipeRepository;
import org.voiculescu.siit.recipes.model.Recipe;
import org.voiculescu.siit.recipes.model.RecipeCategory;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(long id) {
        Optional<Recipe> result = recipeRepository.findById(id);
        Recipe recipe;
        if (result.isPresent()) {
            recipe = result.get();
        } else {
            throw new RuntimeException("Could not find recipe with id: " + id);
        }
        return recipe;
    }

    @Override
    public void save(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    @Override
    public void deleteById(long id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public List<Recipe> findByCategory(RecipeCategory category) {
        List<Recipe> results;
        if (category != null) {
            results = recipeRepository.findAllByRecipeCategoryOrderByLastModifiedDesc(category);
        } else {
            results = findAll();
        }
        return results;
    }

}
