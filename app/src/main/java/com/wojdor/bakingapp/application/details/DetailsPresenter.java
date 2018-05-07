package com.wojdor.bakingapp.application.details;

import com.wojdor.bakingapp.domain.Recipe;

public class DetailsPresenter implements DetailsContract.Presenter {

    private final DetailsContract.View view;
    private final Recipe recipe;

    DetailsPresenter(DetailsContract.View view, Recipe recipe) {
        this.view = view;
        this.recipe = recipe;
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
