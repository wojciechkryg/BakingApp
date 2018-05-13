package com.wojdor.bakingapp.application.recipedetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.application.recipedetails.stepdetails.StepDetailsActivity;
import com.wojdor.bakingapp.application.recipedetails.stepdetails.StepDetailsFragment;
import com.wojdor.bakingapp.application.recipedetails.steps.StepsFragment;
import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.domain.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wojdor.bakingapp.application.utils.Extras.RECIPE_EXTRA;
import static com.wojdor.bakingapp.application.utils.Extras.STEP_EXTRA;

public class RecipeDetailsActivity extends BaseActivity implements RecipeDetailsContract.View {

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
        setTitle(recipe.getName());
        StepsFragment stepsFragment = StepsFragment.newInstance(recipe);
        replaceFragment(R.id.activity_recipe_details_steps_container_fl, stepsFragment);
        stepsFragment.setOnStepClickListener(step -> presenter.showStepDetails(step));
    }

    @Override
    public void openStepDetails(Step step) {
        if (isTwoPanelView()) {
            handleSelectedStepOnTwoPanelView(step);
        } else {
            handleSelectedStepOnOnePanelView(step);
        }
    }

    private void handleSelectedStepOnTwoPanelView(Step step) {
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(step);
        replaceFragment(R.id.activity_recipe_details_step_details_container_fl, stepDetailsFragment);
    }

    private void handleSelectedStepOnOnePanelView(Step step) {
        Intent intent = new Intent(this, StepDetailsActivity.class);
        intent.putExtra(STEP_EXTRA, step);
        startActivity(intent);
    }

    private boolean isTwoPanelView() {
        return stepDetailsContainerFl != null;
    }
}
