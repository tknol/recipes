package tnk.recipes.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tnk.recipes.commands.RecipeCommand;
import tnk.recipes.domain.Recipe;
import tnk.recipes.exception.NotFoundException;
import tnk.recipes.services.RecipeService;

@Slf4j
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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){

        log.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");

        return modelAndView;
    }
}
