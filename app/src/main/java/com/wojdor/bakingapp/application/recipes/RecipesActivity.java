package com.wojdor.bakingapp.application.recipes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.application.recipedetails.RecipeDetailsActivity;
import com.wojdor.bakingapp.application.splash.SplashActivity;
import com.wojdor.bakingapp.domain.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wojdor.bakingapp.application.utils.Extras.RECIPE_EXTRA;

public class RecipesActivity extends BaseActivity implements RecipesContract.View {

    private static final String LAST_RECIPES_RV_STATE = "LAST_RECIPES_RV_STATE";
    private static final int COLUMN_WIDTH_DIVIDER = 500;
    private static final int WIDGET_REQUEST_CODE = 0;
    private static final int WIDGET_DEFAULT_FLAG = 0;

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
        adapter = new RecipesAdapter(getOnRecipeClickListener());
        recipesRv.setLayoutManager(new GridLayoutManager(this, calculateNumberOfColumns()));
        recipesRv.setAdapter(adapter);
    }

    @NonNull
    private RecipesAdapter.OnItemClickListener getOnRecipeClickListener() {
        if (isChoosingRecipeForWidget()) {
            return recipe -> presenter.setupRecipeWidget(recipe);
        }
        return recipe -> presenter.showRecipeDetails(recipe);
    }

    private boolean isChoosingRecipeForWidget() {
        return getWidgetId() != AppWidgetManager.INVALID_APPWIDGET_ID;
    }

    private int getWidgetId() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return AppWidgetManager.INVALID_APPWIDGET_ID;
        }
        return extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
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

    @Override
    public void openRecipeDetails(Recipe recipe) {
        Intent intent = new Intent(this, RecipeDetailsActivity.class);
        intent.putExtra(RECIPE_EXTRA, recipe);
        startActivity(intent);
    }

    @Override
    public void showRecipeWidget(String recipeName, String formattedRecipeIngredients) {
        updateRecipeWidget(recipeName, formattedRecipeIngredients);
        setupResult();
        finish();
    }

    private void updateRecipeWidget(String recipeName, String formattedRecipeIngredients) {
        RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_recipe);
        setupWidgetRecipeName(views, recipeName);
        setupWidgetIngredients(views, formattedRecipeIngredients);
        setupWidgetOnClick(views);
        AppWidgetManager.getInstance(this).updateAppWidget(getWidgetId(), views);
    }

    private void setupWidgetRecipeName(RemoteViews views, String recipeName) {
        views.setTextViewText(R.id.widget_recipe_recipe_name_tv, recipeName);
    }

    private void setupWidgetIngredients(RemoteViews views, String formattedRecipeIngredients) {
        views.setTextViewText(R.id.widget_recipe_ingredients_tv, formattedRecipeIngredients);
    }

    private void setupWidgetOnClick(RemoteViews views) {
        views.setOnClickPendingIntent(R.id.widget_recipe_container_rl, createOnClickPendingIntent());
    }

    private PendingIntent createOnClickPendingIntent() {
        Intent intent = new Intent(this, SplashActivity.class);
        return PendingIntent.getActivity(this, WIDGET_REQUEST_CODE, intent, WIDGET_DEFAULT_FLAG);
    }

    private void setupResult() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, getWidgetId());
        setResult(RESULT_OK, resultValue);
    }
}
