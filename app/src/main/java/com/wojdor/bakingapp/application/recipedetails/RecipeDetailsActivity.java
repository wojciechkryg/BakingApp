package com.wojdor.bakingapp.application.recipedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.domain.Recipe;

import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsContract.View {

    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";

    private RecipeDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ButterKnife.bind(this);
        setupPresenter();
    }

    @Override
    public void setupPresenter() {
        Recipe recipe = getIntent().getParcelableExtra(RECIPE_EXTRA);
        presenter = new RecipeDetailsPresenter(this, recipe);
        presenter.onAttachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }
}
