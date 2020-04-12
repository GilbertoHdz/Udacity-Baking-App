package com.manitos.dev.gilinhobakingapp.features.components;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.api.models.Step;

public class BakeStepFragment extends Fragment {

    public static final String ARG_STEP = "bake.step.arg.step";

    private BakeStepViewModel mViewModel;

    public static BakeStepFragment newInstance(Step step) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(BakeStepFragment.ARG_STEP, step);

        BakeStepFragment fragment = new BakeStepFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bake_step_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BakeStepViewModel.class);
        // TODO: Use the ViewModel
    }

}
