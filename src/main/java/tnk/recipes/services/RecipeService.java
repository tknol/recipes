package tnk.recipes.services;

import tnk.recipes.domain.Recipe;

import java.util.Optional;

public interface RecipeService {
    Iterable<Recipe> getRecipes();

    Recipe findById(Long l);
}
