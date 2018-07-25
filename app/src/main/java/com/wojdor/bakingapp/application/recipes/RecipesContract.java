package com.wojdor.bakingapp.application.recipes;

import com.wojdor.bakingapp.application.base.BaseContract;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

public interface RecipesContract {

    interface View extends BaseContract.View {

        void showRecipes(List<Recipe> recipes);

        void restoreRecipesListState();

        void openRecipeDetails(Recipe recipe);

        void showRecipeWidget(Recipe recipe);
    }

    interface Presenter extends BaseContract.Presenter {

        void showRecipeDetails(Recipe recipe);

        void setupRecipeWidget(Recipe recipe);
    }
}
