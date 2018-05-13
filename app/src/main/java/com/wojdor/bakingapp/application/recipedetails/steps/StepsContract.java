package com.wojdor.bakingapp.application.recipedetails.steps;

import com.wojdor.bakingapp.application.base.BaseContract;
import com.wojdor.bakingapp.domain.Ingredient;
import com.wojdor.bakingapp.domain.Step;

import java.util.List;

public interface StepsContract {

    interface View extends BaseContract.View {

        void showIngredients(List<Ingredient> ingredients);

        void showSteps(List<Step> steps);
    }

    interface Presenter extends BaseContract.Presenter {

    }
}
