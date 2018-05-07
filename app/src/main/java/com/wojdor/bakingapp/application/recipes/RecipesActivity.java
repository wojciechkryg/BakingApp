package com.wojdor.bakingapp.application.recipes;

import android.os.Bundle;
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

    private static final int COLUMN_WIDTH_DIVIDER = 500;

    @BindView(R.id.activity_recipes_recipes_rv)
    RecyclerView recipesRv;

    private RecipesContract.Presenter presenter;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        setupPresenter();
        setupRecipesRv();
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
    }
}
