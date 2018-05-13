package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseFragment;
import com.wojdor.bakingapp.domain.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wojdor.bakingapp.application.utils.Extras.STEP_EXTRA;

public class StepDetailsFragment extends BaseFragment implements StepDetailsContract.View {

    @BindView(R.id.fragment_step_details_description_tv)
    TextView descriptionTv;

    private StepDetailsContract.Presenter presenter;

    public static StepDetailsFragment newInstance(Step step) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STEP_EXTRA, step);
        StepDetailsFragment fragment = new StepDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_step_details, container, false);
        ButterKnife.bind(this, view);
        setupPresenter();
        return view;
    }

    @Override
    public void setupPresenter() {
        Step step = getArguments().getParcelable(STEP_EXTRA);
        presenter = new StepDetailsPresenter(this, step);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onAttachView();
    }

    @Override
    public void showStepDetails(Step step) {
        descriptionTv.setText(step.getDescription());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetachView();
    }
}
