package com.wojdor.bakingapp.data.model;

import com.google.gson.annotations.Expose;

public class IngredientModel {

    @Expose
    private int quantity;
    @Expose
    private String measure;
    @Expose
    private String ingredient;

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }
}
