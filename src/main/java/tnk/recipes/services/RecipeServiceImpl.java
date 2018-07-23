package tnk.recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tnk.recipes.domain.Recipe;
import tnk.recipes.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Iterable<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }
}
