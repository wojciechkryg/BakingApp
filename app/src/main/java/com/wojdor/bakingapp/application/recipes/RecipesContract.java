package com.wojdor.bakingapp.application.recipes;

import com.wojdor.bakingapp.application.base.BasePresenter;
import com.wojdor.bakingapp.application.base.BaseView;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

public interface RecipesContract {

    interface View extends BaseView {

        void showRecipes(List<Recipe> recipes);

        void restoreRecipesListState();
    }

    interface Presenter extends BasePresenter {

        void showRecipeDetails(Recipe recipe);
    }
}
