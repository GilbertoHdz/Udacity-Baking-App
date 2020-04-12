package com.manitos.dev.gilinhobakingapp.features.bakedetail;

import android.content.Intent;
import android.os.Bundle;

import com.manitos.dev.gilinhobakingapp.api.models.Ingredient;
import com.manitos.dev.gilinhobakingapp.api.models.Step;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeUiItems;
import com.manitos.dev.gilinhobakingapp.features.components.BakeIngredientsFragment;
import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeRecipeActivity;
import com.manitos.dev.gilinhobakingapp.features.components.BakeStepFragment;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.view.MenuItem;

import java.util.List;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BakeRecipeActivity}.
 */
public class BakeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bake_detail_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            Intent intent = getIntent();

            if (intent.hasExtra(BakeIngredientsFragment.ARG_INGREDIENTS)) {
                showIngredientsScreen((BakeUiItems) intent.getSerializableExtra(BakeIngredientsFragment.ARG_INGREDIENTS));
            } else if (intent.hasExtra(BakeStepFragment.ARG_STEP)) {
                showStepScreen((Step) intent.getSerializableExtra(BakeStepFragment.ARG_STEP));
            } else {
                throw new IllegalArgumentException("the params shouldn't be null");
            }
        }
    }

    private void showStepScreen(Step step) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, BakeStepFragment.newInstance(step))
                .commit();
    }

    private void showIngredientsScreen(BakeUiItems bakeItem) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_detail_container, BakeIngredientsFragment.newInstance(bakeItem))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, BakeRecipeActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
