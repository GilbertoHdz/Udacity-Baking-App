package com.manitos.dev.gilinhobakingapp.features.bakerecipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.features.bakedetail.BakeDetailActivity;
import com.manitos.dev.gilinhobakingapp.features.components.BakeIngredientsFragment;
import com.manitos.dev.gilinhobakingapp.features.components.BakeStepFragment;

import java.util.List;

/**
 * Created by gilberto hdz on 12/04/20.
 */
public class BakeRecipeAdapter
        extends RecyclerView.Adapter<BakeRecipeAdapter.ViewHolder> {

    private final BakeRecipeActivity mParentActivity;
    private final List<BakeUiItems> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BakeUiItems item = (BakeUiItems) view.getTag();
            if (mTwoPane) {
                Fragment fragment;
                if (item.getStep() != null) {
                    fragment = BakeStepFragment.newInstance(item.getStep());
                } else if (item.getIngredients() != null) {
                    fragment = BakeIngredientsFragment.newInstance(item);
                } else {
                    throw new IllegalStateException("no state in action selected");
                }

                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();

            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, BakeDetailActivity.class);
                if (item.getStep() != null) {
                    intent.putExtra(BakeStepFragment.ARG_STEP , item.getStep());
                } else if (item.getIngredients() != null) {
                    intent.putExtra(BakeIngredientsFragment.ARG_INGREDIENTS , item);
                } else {
                    throw new IllegalStateException("no state in action selected for intent");
                }

                context.startActivity(intent);
            }
        }
    };

    BakeRecipeAdapter(
            BakeRecipeActivity parent,
            List<BakeUiItems> items,
            boolean twoPane
    ) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bake_recipe_item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getStepType());
        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.bake_recipe_item_list_content_desc);
        }
    }
}
