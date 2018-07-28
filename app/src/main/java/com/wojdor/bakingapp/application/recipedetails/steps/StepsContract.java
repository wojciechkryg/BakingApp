package com.wojdor.bakingapp.application.recipedetails.steps;

import com.wojdor.bakingapp.application.base.BaseContract;
import com.wojdor.bakingapp.domain.Step;

import java.util.List;

public interface StepsContract {

    interface View extends BaseContract.View {

        void showSteps(List<Step> steps);

        void showIngredients(String formattedIngredients);
    }

    interface Presenter extends BaseContract.Presenter {

    }
}
