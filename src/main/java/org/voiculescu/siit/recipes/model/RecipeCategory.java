package org.voiculescu.siit.recipes.model;

import lombok.Getter;

@Getter
public enum RecipeCategory {

    SOUP("Soup","soup"),
    MAIN_DISH("Main Dish","main-dish"),
    SALAD("Salad","salad"),
    DESSERT("Dessert","dessert"),
    MISCELLANEOUS("Miscellaneous","misc");

    private final String displayValue;
    private final String path;

    RecipeCategory(String displayValue,String path) {
        this.displayValue = displayValue;this.path=path;
    }

    public static RecipeCategory getRecipeCategoryByPath(String temp){
        for(RecipeCategory category: RecipeCategory.values()){
            if (category.getPath().contentEquals(temp.toLowerCase())) return category;
        }
        return null;
    }
}
