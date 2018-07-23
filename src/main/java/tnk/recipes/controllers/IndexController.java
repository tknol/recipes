package tnk.recipes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tnk.recipes.domain.Recipe;
import tnk.recipes.services.RecipeService;

@Controller
public class IndexController {

    private RecipeService recipeService;

    @Autowired
    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndex(Model model){

        Iterable<Recipe> recipes = recipeService.getRecipes();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}
