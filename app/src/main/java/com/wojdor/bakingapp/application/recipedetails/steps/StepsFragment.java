package com.wojdor.bakingapp.application.recipedetails.steps;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseFragment;
import com.wojdor.bakingapp.domain.Recipe;
import com.wojdor.bakingapp.domain.Step;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wojdor.bakingapp.application.utils.Extras.RECIPE_EXTRA;

public class StepsFragment extends BaseFragment implements StepsContract.View {

    @BindView(R.id.fragment_steps_container_nsv)
    NestedScrollView containerNsv;
    @BindView(R.id.fragment_steps_ingredients_tv)
    TextView ingredientsTv;
    @BindView(R.id.fragment_steps_steps_rv)
    RecyclerView stepsRv;

    private StepsContract.Presenter presenter;
    private StepsAdapter adapter;
    private OnStepClickListener onStepClickListener;

    public static StepsFragment newInstance(Recipe recipe) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RECIPE_EXTRA, recipe);
        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_steps, container, false);
        ButterKnife.bind(this, view);
        setupStatesRv();
        setupPresenter();
        return view;
    }

    private void setupStatesRv() {
        adapter = new StepsAdapter(step -> onStepClickListener.onStepSelected(step));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stepsRv.setLayoutManager(layoutManager);
        addDividerToStepsRv(layoutManager);
        stepsRv.setAdapter(adapter);
        stepsRv.setNestedScrollingEnabled(false);
    }

    @Override
    public void setupPresenter() {
        Recipe recipe = getArguments().getParcelable(RECIPE_EXTRA);
        presenter = new StepsPresenter(this, recipe);
    }

    private void addDividerToStepsRv(LinearLayoutManager layoutManager) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(stepsRv.getContext(),
                layoutManager.getOrientation());
        stepsRv.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttachView();
    }

    @Override
    public void showIngredients(String formattedIngredients) {
        ingredientsTv.setText(formattedIngredients);
    }

    @Override
    public void showSteps(List<Step> steps) {
        adapter.setSteps(steps);
    }

    public void setOnStepClickListener(OnStepClickListener onStepClickListener) {
        this.onStepClickListener = onStepClickListener;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetachView();
    }
}
