package com.manitos.dev.gilinhobakingapp.features.components;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.api.models.Step;

public class BakeStepFragment extends Fragment {

    public static final String ARG_STEP = "bake.step.arg.step";

    private TextView bakeRecipeStep;
    private PlayerView playerView;
    private SimpleExoPlayer exoPlayer;

    private Step step;

    public static BakeStepFragment newInstance(Step step) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(BakeStepFragment.ARG_STEP, step);

        BakeStepFragment fragment = new BakeStepFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_STEP)) {
            step = (Step) getArguments().getSerializable(ARG_STEP);
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(step.getShortDescription());
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_STEP, step);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bake_step_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bakeRecipeStep = (TextView) view.findViewById(R.id.bake_step_recipe);
        playerView = (PlayerView) view.findViewById(R.id.playerView);

        bakeRecipeStep.setText(step.getDescription());
    }

    private void initializePlayer() {
        exoPlayer = new SimpleExoPlayer.Builder(requireContext()).build();
        playerView.setPlayer(exoPlayer);
        exoPlayer.seekTo(0);

        MediaSource mediaSource = buildMediaSource(Uri.parse(step.getVideoURL()));

        // Prepare the player with the source.
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri uri) {
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                requireContext(),
                Util.getUserAgent(requireContext(), " com.manitos.dev.gilinhobakingapp")
        );

        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(step.getVideoURL()));

        // Prepare the player with the source.
        return videoSource;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    @Override
    public void onDestroyView() {
        releasePlayer();
        super.onDestroyView();
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.release();
            exoPlayer = null;
        }
    }
}
