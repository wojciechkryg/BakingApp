package com.wojdor.bakingapp.application.recipedetails.steps;

import com.wojdor.bakingapp.application.base.BasePresenter;
import com.wojdor.bakingapp.application.base.BaseView;
import com.wojdor.bakingapp.domain.Ingredient;
import com.wojdor.bakingapp.domain.Step;

import java.util.List;

public interface StepsContract {

    interface View extends BaseView {

        void showIngredients(List<Ingredient> ingredients);

        void showSteps(List<Step> steps);
    }

    interface Presenter extends BasePresenter {

    }
}
