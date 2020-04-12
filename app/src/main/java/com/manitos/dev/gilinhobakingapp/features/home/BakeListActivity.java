package com.manitos.dev.gilinhobakingapp.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.api.models.Bake;
import com.manitos.dev.gilinhobakingapp.features.MainViewModel;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeRecipeActivity;

import java.util.List;

public class BakeListActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    public static final String BAKE_KEY = "Bake.List.Activity.bake.item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        getBakesObserver();
    }

    private void getBakesObserver() {
        mainViewModel.getBakes().observe(this, new Observer<List<Bake>>() {
            @Override
            public void onChanged(List<Bake> bakes) {
                View recyclerView = findViewById(R.id.recycler_bake_list);
                setupRecyclerView((RecyclerView) recyclerView, bakes);
            }
        });
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<Bake> bakes) {
        int totalCol = this.getResources().getInteger(R.integer.bake_list_num_col);
        RecyclerView.LayoutManager lLayout = new GridLayoutManager(this, totalCol);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(new BakeListAdapter(bakes));
    }

    public static class BakeListAdapter
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
}
