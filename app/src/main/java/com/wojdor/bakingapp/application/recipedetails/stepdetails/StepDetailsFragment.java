package com.wojdor.bakingapp.application.recipedetails.stepdetails;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.base.BaseFragment;
import com.wojdor.bakingapp.domain.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wojdor.bakingapp.application.utils.Extras.STEP_EXTRA;

public class StepDetailsFragment extends BaseFragment implements StepDetailsContract.View {

    private static final String PLAYER_USER_AGENT = "exoplayer";
    private static final String SAVED_STATE_EXTRA = "com.wojdor.bakingapp.application.recipedetails.stepdetails.SAVED_STATE_EXTRA";
    private static final String LAST_PLAYER_POSITION_EXTRA = "com.wojdor.bakingapp.application.recipedetails.stepdetails.LAST_PLAYER_POSITION_EXTRA";

    @BindView(R.id.fragment_step_details_video_pv)
    PlayerView videoPv;
    @BindView(R.id.fragment_step_details_description_tv)
    TextView descriptionTv;

    private StepDetailsContract.Presenter presenter;
    private ExoPlayer player;
    private long lastPlayerPosition = 0;

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
        restorePlayerState(savedInstanceState);
        return view;
    }

    @Override
    public void setupPresenter() {
        Step step = getArguments().getParcelable(STEP_EXTRA);
        presenter = new StepDetailsPresenter(this, step);
    }

    private void restorePlayerState(Bundle savedInstanceState) {
        if (savedInstanceState == null) return;
        Bundle savedState = savedInstanceState.getBundle(SAVED_STATE_EXTRA);
        if (savedState == null) return;
        lastPlayerPosition = savedState.getLong(LAST_PLAYER_POSITION_EXTRA);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttachView();
    }

    @Override
    public void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(getContext()),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            player.setPlayWhenReady(true);
            player.seekTo(lastPlayerPosition);
            videoPv.setPlayer(player);
        }
    }

    @Override
    public void releasePlayer() {
        if (player == null) return;
        player.release();
    }

    @Override
    public void showStepDetails(Step step) {
        descriptionTv.setText(step.getDescription());
        loadVideo(step.getVideoUrl());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBundle(SAVED_STATE_EXTRA, saveState());
    }

    private Bundle saveState() {
        Bundle state = new Bundle();
        state.putLong(LAST_PLAYER_POSITION_EXTRA, player.getCurrentPosition());
        return state;
    }

    private void loadVideo(String videoUrl) {
        if (videoUrl.isEmpty()) {
            videoPv.setVisibility(View.GONE);
            return;
        }
        Uri uri = Uri.parse(videoUrl);
        MediaSource mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory(PLAYER_USER_AGENT))
                .createMediaSource(uri);
        player.prepare(mediaSource, false, false);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onDetachView();
    }
}
