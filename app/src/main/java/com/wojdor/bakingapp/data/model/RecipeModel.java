package com.wojdor.bakingapp.data.model;

import com.google.gson.annotations.Expose;

import java.util.List;

public class RecipeModel {

    @Expose
    private int id;
    @Expose
    private String name;
    @Expose
    private List<IngredientModel> ingredients;
    @Expose
    private List<StepModel> steps;
    @Expose
    private int servings;
    @Expose
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<IngredientModel> getIngredients() {
        return ingredients;
    }

    public List<StepModel> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}
