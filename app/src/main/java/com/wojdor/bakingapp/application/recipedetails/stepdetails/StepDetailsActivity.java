package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.domain.Step;

import static com.wojdor.bakingapp.application.utils.Extras.STEP_EXTRA;

public class StepDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_details);
        Step step = getIntent().getParcelableExtra(STEP_EXTRA);
        setTitle(step.getShortDescription());
        showStepDetailsFragment(step);
    }

    private void showStepDetailsFragment(Step step) {
        if (isFragmentNotAddedToLayout(R.id.activity_step_details_container_fl)) {
            StepDetailsFragment stepDetailsFragment = StepDetailsFragment.newInstance(step);
            replaceFragment(R.id.activity_step_details_container_fl, stepDetailsFragment);
        }
    }
}
