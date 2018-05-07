package com.wojdor.bakingapp.application.details;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;
import com.wojdor.bakingapp.domain.Recipe;

import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {

    public static final String RECIPE_EXTRA = "RECIPE_EXTRA";

    private DetailsContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        setupPresenter();
    }

    @Override
    public void setupPresenter() {
        Recipe recipe = getIntent().getParcelableExtra(RECIPE_EXTRA);
        presenter = new DetailsPresenter(this, recipe);
        presenter.onAttachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetachView();
    }
}
