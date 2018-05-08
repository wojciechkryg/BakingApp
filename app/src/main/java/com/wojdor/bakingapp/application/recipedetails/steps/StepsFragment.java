package com.wojdor.bakingapp.application.recipedetails.steps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wojdor.bakingapp.application.base.BaseFragment;

public class StepsFragment extends BaseFragment implements StepsContract.View {

    private StepsContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setupPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupPresenter() {
        presenter = new StepsPresenter(this);
        presenter.onAttachView();
    }
}
