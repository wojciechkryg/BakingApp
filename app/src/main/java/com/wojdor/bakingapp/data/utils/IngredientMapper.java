package com.wojdor.bakingapp.data.utils;

import com.wojdor.bakingapp.data.model.IngredientModel;
import com.wojdor.bakingapp.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

public final class IngredientMapper {

    private static IngredientMapper instance;

    private IngredientMapper() {
    }

    public static IngredientMapper getInstance() {
        if (instance == null) {
            instance = new IngredientMapper();
        }
        return instance;
    }

    private Ingredient map(IngredientModel model) {
        return new Ingredient(
                model.getQuantity(),
                model.getMeasure(),
                model.getIngredient()
        );
    }

    public List<Ingredient> map(List<IngredientModel> models) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientModel model : models) {
            ingredients.add(map(model));
        }
        return ingredients;
    }
}