package com.wojdor.bakingapp.data.utils;

import com.wojdor.bakingapp.data.model.RecipeModel;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.ArrayList;
import java.util.List;

public final class RecipeMapper {

    private static RecipeMapper instance;

    private RecipeMapper() {
    }

    public static RecipeMapper getInstance() {
        if (instance == null) {
            instance = new RecipeMapper();
        }
        return instance;
    }

    private Recipe map(RecipeModel model) {
        return new Recipe(
                model.getId(),
                model.getName(),
                IngredientMapper.getInstance().map(model.getIngredients()),
                StepMapper.getInstance().map(model.getSteps()),
                model.getServings(),
                model.getImage()
        );
    }

    public List<Recipe> map(List<RecipeModel> models) {
        List<Recipe> recipes = new ArrayList<>();
        for (RecipeModel model : models) {
            recipes.add(map(model));
        }
        return recipes;
    }
}
