package com.wojdor.bakingapp.application.recipedetails;

import com.wojdor.bakingapp.application.base.BaseContract;
import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.domain.Step;

public interface RecipeDetailsContract {

    interface View extends BaseContract.View {

        void showRecipeDetails(Recipe recipe);

        void openStepDetails(Step step);
    }

    interface Presenter extends BaseContract.Presenter {

        void showStepDetails(Step step);
    }
}
