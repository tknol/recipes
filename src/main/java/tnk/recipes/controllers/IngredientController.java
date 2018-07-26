package tnk.recipes.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tnk.recipes.services.RecipeService;

@Slf4j
@Controller
public class IngredientController {

    private RecipeService recipeService;

    public IngredientController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String getIngredients(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }
}
