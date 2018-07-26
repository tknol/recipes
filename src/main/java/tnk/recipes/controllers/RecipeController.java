package tnk.recipes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tnk.recipes.commands.RecipeCommand;
import tnk.recipes.domain.Recipe;
import tnk.recipes.services.RecipeService;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("recipe/{id}/view")
    public String getRecipe(@PathVariable String id,
            Model model){

        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);

        return "recipe/view";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(Long.valueOf(id)));

        return("recipe/recipeform");
    }

    @PostMapping
    @RequestMapping("recipe")
    public String getUpdateView(@ModelAttribute RecipeCommand recipeCommand){

        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
 
        return "redirect:/recipe/" + savedRecipe.getId() + "/view";
    }

    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model){

        recipeService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
