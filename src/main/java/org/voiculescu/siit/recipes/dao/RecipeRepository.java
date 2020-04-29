package org.voiculescu.siit.recipes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voiculescu.siit.recipes.model.*;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    List<Recipe> findAllByRecipeCategoryOrderByLastModifiedDesc(RecipeCategory category);
}
