package org.voiculescu.siit.recipes.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.voiculescu.siit.recipes.model.*;
import org.voiculescu.siit.recipes.service.RecipeService;
import org.voiculescu.siit.recipes.util.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class RecipesController {

    private final RecipeService recipeService;

    @Autowired
    public RecipesController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(Mappings.HOME)
    public ModelAndView showHome() {
        ModelAndView model = new ModelAndView(Views.HOME);
        List<Recipe> recipes = recipeService.findAll();
        model.addObject(Attributes.RECIPES, recipes);
        model.addObject(Attributes.TITLE,"Home");
        return model;
    }

    @GetMapping(Mappings.ADD)
    public ModelAndView addRecipeForm() {
        ModelAndView model = new ModelAndView(Views.ADD);
        model.addObject(Attributes.RECIPE, new Recipe());
        return model;
    }

    @PostMapping(Mappings.ADD)
    public ModelAndView processAddRecipeForm(@Valid @ModelAttribute(Attributes.RECIPE) Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView(Views.ADD);
        recipeService.save(recipe);
        return new ModelAndView(Mappings.REDIRECT + Mappings.HOME);
    }

    @GetMapping(Mappings.SHOW_BY_CATEGORY)
    public ModelAndView showCategory(@PathVariable(Mappings.CATEGORY) String category) {
        RecipeCategory recipeCategory = RecipeCategory.getRecipeCategoryByPath(category);
        if (recipeCategory == null) {
            return new ModelAndView(Mappings.REDIRECT + Mappings.HOME);
        }
        ModelAndView model = new ModelAndView(Views.HOME);
        List<Recipe> recipes = recipeService.findByCategory(recipeCategory);
        model.addObject(Attributes.RECIPES, recipes);
        model.addObject(Attributes.TITLE,recipeCategory.getDisplayValue());
        return model;
    }

    @GetMapping(Mappings.VIEW_BY_ID)
    public ModelAndView showRecipe(@PathVariable(Mappings.ID) long id) {
        Recipe recipe = recipeService.findById(id);
        ModelAndView modelAndView = new ModelAndView(Views.SHOW_RECIPE);
        modelAndView.addObject(Attributes.RECIPE, recipe);
        return modelAndView;
    }

    @GetMapping(Mappings.EDIT_BY_ID)
    public ModelAndView showEditRecipe(@PathVariable(Mappings.ID) long id) {
        Recipe recipe = recipeService.findById(id);
        ModelAndView modelAndView = new ModelAndView(Views.EDIT);
        modelAndView.addObject(Attributes.RECIPE, recipe);
        return modelAndView;
    }

    @PostMapping(Mappings.UPDATE)
    public ModelAndView processEditRecipeForm(@Valid @ModelAttribute(Attributes.RECIPE) Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ModelAndView(Views.EDIT);
        recipeService.save(recipe);
        return new ModelAndView(Mappings.REDIRECT + Mappings.VIEW + "/" + recipe.getId());
    }

    @GetMapping(Mappings.DELETE_BY_ID)
    public String deleteById(@PathVariable(Mappings.ID) long id) {
        recipeService.deleteById(id);
        return Mappings.REDIRECT + Mappings.HOME;
    }

    @RequestMapping(Mappings.ABOUT)
    public ModelAndView showAbout() {
        ModelAndView model = new ModelAndView(Views.ABOUT);
        model.addObject(Attributes.TITLE,"About me");
        return model;
    }

}
