package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import com.wojdor.bakingapp.application.base.BaseContract;
import com.wojdor.bakingapp.domain.Step;

public interface StepDetailsContract {

    interface View extends BaseContract.View {

        void showStepDetails(Step step);
    }

    interface Presenter extends BaseContract.Presenter {

    }
}
