package tnk.recipes.services;

import tnk.recipes.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) throws Exception;

}
