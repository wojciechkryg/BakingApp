package com.wojdor.bakingapp.application.recipedetails;

import com.wojdor.bakingapp.application.base.BasePresenter;
import com.wojdor.bakingapp.application.base.BaseView;
import com.wojdor.bakingapp.domain.Recipe;

public interface RecipeDetailsContract {

    interface View extends BaseView {

        void showRecipeDetails(Recipe recipe);
    }

    interface Presenter extends BasePresenter {

    }
}
