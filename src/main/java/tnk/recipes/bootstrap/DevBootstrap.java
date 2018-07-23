package tnk.recipes.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tnk.recipes.domain.Difficulty;
import tnk.recipes.domain.Ingredient;
import tnk.recipes.domain.Note;
import tnk.recipes.domain.Recipe;
import tnk.recipes.repositories.CategoryRepository;
import tnk.recipes.repositories.RecipeRepository;
import tnk.recipes.repositories.UnitOfMeasureRepository;

import java.math.BigDecimal;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CategoryRepository categoryRepository;
    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    private void initData(){
        Recipe taco = getTaco();
        Recipe burger = getBurger();

        recipeRepository.save(taco);
        recipeRepository.save(burger);
    }

    private Recipe getBurger() {
        Recipe recipe = getRecipe("Burgers", "Put the patty in a bun eat it", Difficulty.MODERATE);
        recipe.getCategories().add(categoryRepository.findByDescription("American").get());
        recipe.getCategories().add(categoryRepository.findByDescription("Fast Food").get());
        Note note = getNote("Don't forget to grill 'em");
        recipe.setNote(note);
        Ingredient bun = getIngredient(recipe, "burger bun", BigDecimal.valueOf(1));
        recipe.getIngredients().add(bun);
        Ingredient patty = getIngredient(recipe, "patty", BigDecimal.valueOf(1));
        recipe.getIngredients().add(patty);
        return recipe;
    }

    private Recipe getTaco() {
        Recipe recipe = getRecipe("Tacos", "Put the tacos together and eat 'em", Difficulty.EASY);
        recipe.getCategories().add(categoryRepository.findByDescription("Mexican").get());
        recipe.getCategories().add(categoryRepository.findByDescription("Fast Food").get());
        Note note = getNote("Don't forget to eat 'em");
        recipe.setNote(note);
        Ingredient shell = getIngredient(recipe, "taco shell", BigDecimal.valueOf(2));
        recipe.getIngredients().add(shell);
        return recipe;
    }

    private Recipe getRecipe(String description, String directions, Difficulty difficulty) {
        Recipe recipe = new Recipe();
        recipe.setDescription(description);
        recipe.setDirections(directions);
        recipe.setCookTime(1);
        recipe.setPrepTime(1);
        recipe.setServings(6);
        recipe.setSource("braiiins");
        recipe.setUrl("et go home");
        recipe.setDifficulty(difficulty);
        return recipe;
    }

    private Ingredient getIngredient(Recipe recipe, String description, BigDecimal amount) {
        Ingredient shell = new Ingredient();
        shell.setAmount(amount);
        shell.setDescription(description);
        shell.setRecipe(recipe);
        shell.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Each").get());
        return shell;
    }

    private Note getNote(String description) {
        Note note = new Note();
        note.setRecipeNote(description);
        return note;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
