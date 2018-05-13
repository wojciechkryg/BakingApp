package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.domain.Step;

import static com.wojdor.bakingapp.application.utils.Extras.STEP_EXTRA;

public class StepDetailsActivity extends BaseActivity {

    private Step step;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        handleExtras();
        setTitle(step.getShortDescription());
        showStepDetailsFragment();
    }

    private void handleExtras() {
        step = getIntent().getParcelableExtra(STEP_EXTRA);
    }

    private void showStepDetailsFragment() {
        StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(step);
        replaceFragment(R.id.activity_step_details_container_fl, stepDetailsFragment);
    }
}
