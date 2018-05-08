package com.wojdor.bakingapp.application.recipedetails.steps;

public class StepsPresenter implements StepsContract.Presenter {

    private final StepsContract.View view;

    StepsPresenter(StepsContract.View view) {
        this.view = view;
    }

    @Override
    public void onAttachView() {

    }

    @Override
    public void onDetachView() {

    }
}
