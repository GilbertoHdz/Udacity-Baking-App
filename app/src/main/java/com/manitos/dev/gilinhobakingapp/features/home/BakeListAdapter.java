package com.manitos.dev.gilinhobakingapp.features.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.api.models.Bake;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeRecipeActivity;

import java.util.List;

import static com.manitos.dev.gilinhobakingapp.features.home.BakeListActivity.BAKE_KEY;

/**
 * Created by gilberto hdz on 12/04/20.
 */
public class BakeListAdapter
        extends RecyclerView.Adapter<BakeListAdapter.ViewHolder> {

    private final List<Bake> mValues;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Bake item = (Bake) v.getTag();

            Context context = v.getContext();
            Intent intent = new Intent(context, BakeRecipeActivity.class);
            intent.putExtra(BAKE_KEY, item);

            context.startActivity(intent);
        }
    };

    public BakeListAdapter(List<Bake> mValues) {
        this.mValues = mValues;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bake_list_container_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mValues.get(position).getName());
        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() { return mValues.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView mTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.item_bake_title);
        }
    }
}