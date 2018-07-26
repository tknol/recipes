package tnk.recipes.services;

import tnk.recipes.commands.RecipeCommand;
import tnk.recipes.domain.Recipe;

import java.util.Optional;

public interface RecipeService {
    Iterable<Recipe> getRecipes();
    Recipe findById(Long l);
    RecipeCommand findRecipeCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
