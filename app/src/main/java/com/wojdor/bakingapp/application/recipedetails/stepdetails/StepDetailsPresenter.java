package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import com.wojdor.bakingapp.domain.Step;

public class StepDetailsPresenter implements StepDetailsContract.Presenter {

    private StepDetailsContract.View view;
    private Step step;

    StepDetailsPresenter(StepDetailsContract.View view, Step step) {
        this.view = view;
        this.step = step;
    }

    @Override
    public void onAttachView() {
        view.initializePlayer();
        view.showStepDetails(step);
    }

    @Override
    public void onDetachView() {
        view.releasePlayer();
    }
}
