package tnk.recipes.services;

import tnk.recipes.domain.Recipe;

public interface RecipeService {
    Iterable<Recipe> getRecipes();
}
