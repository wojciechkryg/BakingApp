package com.wojdor.bakingapp.application.recipedetails.steps;

import com.wojdor.bakingapp.domain.Recipe;

public class StepsPresenter implements StepsContract.Presenter {

    private final StepsContract.View view;
    private final Recipe recipe;

    StepsPresenter(StepsContract.View view, Recipe recipe) {
        this.view = view;
        this.recipe = recipe;
    }

    @Override
    public void onAttachView() {
        view.showIngredients(recipe.getFormattedIngredients());
        view.showSteps(recipe.getSteps());
    }

    @Override
    public void onDetachView() {

    }
}
