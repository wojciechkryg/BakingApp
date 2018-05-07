package com.wojdor.bakingapp.application.recipes;

public class RecipesPresenter implements RecipesContract.Presenter {

    private RecipesContract.View view;

    RecipesPresenter(RecipesContract.View view) {
        this.view = view;
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
