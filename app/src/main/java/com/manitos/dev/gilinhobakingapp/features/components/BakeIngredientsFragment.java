package com.manitos.dev.gilinhobakingapp.features.components;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.api.models.Ingredient;
import com.manitos.dev.gilinhobakingapp.dummy.DummyContent;
import com.manitos.dev.gilinhobakingapp.features.bakedetail.BakeDetailActivity;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeRecipeActivity;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeUiItems;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link BakeRecipeActivity}
 * in two-pane mode (on tablets) or a {@link BakeDetailActivity}
 * on handsets.
 */
public class BakeIngredientsFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_INGREDIENTS = "bake.ingredient.arg.ingredients";

    /**
     * The dummy content this fragment is presenting.
     */
    private BakeUiItems mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public static BakeIngredientsFragment newInstance(BakeUiItems bakeItem) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(BakeIngredientsFragment.ARG_INGREDIENTS, bakeItem);

        BakeIngredientsFragment fragment = new BakeIngredientsFragment();
        fragment.setArguments(arguments);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_INGREDIENTS)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = (BakeUiItems) getArguments().getSerializable(ARG_INGREDIENTS);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getStepType());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.component_bake_container, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            StringBuilder sb = new StringBuilder();
            for (Ingredient ingredient: mItem.getIngredients()) {
                sb
                        .append(" - ")
                        .append(ingredient.getIngredient())
                        .append(" ")
                        .append(ingredient.getQuantity())
                        .append(" ")
                        .append(ingredient.getMeasure())
                        .append("\n");
            }
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(sb);
        }

        return rootView;
    }
}
