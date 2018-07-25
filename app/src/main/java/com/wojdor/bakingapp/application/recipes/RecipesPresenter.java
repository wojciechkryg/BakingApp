package com.wojdor.bakingapp.application.recipes;

import com.wojdor.bakingapp.data.model.RecipeModel;
import com.wojdor.bakingapp.data.service.RecipesService;
import com.wojdor.bakingapp.data.utils.RecipeMapper;
import com.wojdor.bakingapp.domain.Ingredient;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.wojdor.bakingapp.application.utils.Constants.BIG_DOT;
import static com.wojdor.bakingapp.application.utils.Constants.EOL;

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
        view.restoreRecipesListState();
    }

    private <T extends Throwable> void onLoadError(T error) {
        // TODO: Show error in view
    }

    @Override
    public void onDetachView() {
        disposables.clear();
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        view.openRecipeDetails(recipe);
    }

    @Override
    public void setupRecipeWidget(Recipe recipe) {
        view.showRecipeWidget(recipe.getName(), getFormattedIngredients(recipe));
    }

    private String getFormattedIngredients(Recipe recipe) {
        StringBuilder formattedIngredients = new StringBuilder();
        Ingredient ingredient;
        for (int i = 0; i < recipe.getIngredients().size(); i++) {
            ingredient = recipe.getIngredients().get(i);
            formattedIngredients.append(String.format("%s %s", BIG_DOT, ingredient.toString()));
            if (i < recipe.getIngredients().size() - 1) {
                formattedIngredients.append(EOL);
            }
        }
        return formattedIngredients.toString();
    }
}
