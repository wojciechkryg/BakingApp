package com.wojdor.bakingapp.application.recipedetails;

import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.domain.Step;

public class RecipeDetailsPresenter implements RecipeDetailsContract.Presenter {

    private final RecipeDetailsContract.View view;
    private final Recipe recipe;

    RecipeDetailsPresenter(RecipeDetailsContract.View view, Recipe recipe) {
        this.view = view;
        this.recipe = recipe;
    }

    @Override
    public void onAttachView() {
        view.showRecipeDetails(recipe);
    }

    @Override
    public void onDetachView() {

    }

    @Override
    public void showStepDetails(Step step) {
        view.openStepDetails(step);
    }
}
