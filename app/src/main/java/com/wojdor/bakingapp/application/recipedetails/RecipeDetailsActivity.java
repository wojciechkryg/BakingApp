package com.wojdor.bakingapp.application.recipedetails;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.application.recipedetails.steps.StepsFragment;
import com.wojdor.bakingapp.domain.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsContract.View {

    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";

    @Nullable
    @BindView(R.id.activity_recipe_details_step_details_container_fl)
    FrameLayout stepDetailsContainerFl;

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

    @Override
    public void showRecipeDetails(Recipe recipe) {
        StepsFragment fragment = StepsFragment.newInstance(recipe);
        replaceFragment(R.id.activity_recipe_details_steps_container_fl, fragment);
    }
}
