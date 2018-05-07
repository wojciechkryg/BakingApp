package com.wojdor.bakingapp.application.recipes;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends BaseActivity implements RecipesContract.View {

    @BindView(R.id.activity_recipes_recipes_rv)
    RecyclerView recipesRv;

    private RecipesContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);
        ButterKnife.bind(this);
        setupPresenter();
    }

    @Override
    public void setupPresenter() {
        presenter = new RecipesPresenter(this);
        presenter.onAttachView();
    }
}
