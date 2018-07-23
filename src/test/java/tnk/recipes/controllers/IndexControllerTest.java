package tnk.recipes.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import tnk.recipes.domain.Recipe;
import tnk.recipes.services.RecipeService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class IndexControllerTest {

    private IndexController controller;
    @Mock private RecipeService recipeService;
    @Mock private Model model;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        controller = new IndexController(recipeService);

    }

    @Test
    public void getIndex() {
        //given
        Iterable<Recipe> recipes = new ArrayList<>();
        ((ArrayList<Recipe>) recipes).add(new Recipe());
        ((ArrayList<Recipe>) recipes).add(new Recipe());

        //when
        when(recipeService.getRecipes()).thenReturn(recipes);
        String result = controller.getIndex(model);

        //then
        assertEquals("index", result);
         verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute("recipes", recipes);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
}