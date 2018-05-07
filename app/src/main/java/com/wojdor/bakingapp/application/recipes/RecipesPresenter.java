package com.wojdor.bakingapp.application.recipes;

import android.util.Log;

import com.wojdor.bakingapp.data.model.RecipeModel;
import com.wojdor.bakingapp.data.service.RecipesService;
import com.wojdor.bakingapp.data.utils.RecipeMapper;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RecipesPresenter implements RecipesContract.Presenter {

    private final RecipesContract.View view;
    private final CompositeDisposable disposables = new CompositeDisposable();

    RecipesPresenter(RecipesContract.View view) {
        this.view = view;
    }

    @Override
    public void onAttachView() {
        loadRecipes();
    }

    private void loadRecipes() {
        disposables.add(RecipesService.getInstance().getRecipes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoadRecipesResponse, this::onLoadError));
    }

    private void onLoadRecipesResponse(List<RecipeModel> recipeModels) {
        List<Recipe> recipes = RecipeMapper.getInstance().map(recipeModels);
        view.showRecipes(recipes);
    }

    private <T extends Throwable> void onLoadError(T error) {
        // TODO: Show error in view
        Log.d("TEST", error.toString());
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        // TODO: Show recipe details in view
    }
}
