package com.wojdor.bakingapp.application.recipes;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends BaseActivity implements RecipesContract.View {

    private static final String LAST_RECIPES_RV_STATE = "LAST_RECIPES_RV_STATE";
    private static final int COLUMN_WIDTH_DIVIDER = 500;

    @BindView(R.id.activity_recipes_recipes_rv)
    RecyclerView recipesRv;

    private RecipesContract.Presenter presenter;
    private RecipesAdapter adapter;
    private Parcelable recipesRvState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        setupPresenter();
        setupRecipesRv();
        saveLastRecipeRvState(savedInstanceState);
    }

    private void saveLastRecipeRvState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;
        recipesRvState = savedInstanceState.getParcelable(LAST_RECIPES_RV_STATE);
    }

    @Override
    public void setupPresenter() {
        presenter = new RecipesPresenter(this);
        presenter.onAttachView();
    }

    public void setupRecipesRv() {
        adapter = new RecipesAdapter(recipe -> presenter.showRecipeDetails(recipe));
        recipesRv.setLayoutManager(new GridLayoutManager(this, calculateNumberOfColumns()));
        recipesRv.setAdapter(adapter);
    }

    private int calculateNumberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels / COLUMN_WIDTH_DIVIDER;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(LAST_RECIPES_RV_STATE, recipesRv.getLayoutManager().onSaveInstanceState());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
    }

    @Override
    public void restoreRecipesListState() {
        if (recipesRvState == null) return;
        recipesRv.getLayoutManager().onRestoreInstanceState(recipesRvState);
    }
}
