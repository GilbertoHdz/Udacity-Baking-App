package com.manitos.dev.gilinhobakingapp.features.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.dummy.DummyContent;
import com.manitos.dev.gilinhobakingapp.features.bakerecipe.BakeRecipeActivity;

import java.util.List;

public class BakeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View recyclerView = findViewById(R.id.recycler_bake_list);
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        int totalCol = this.getResources().getInteger(R.integer.bake_list_num_col);
        RecyclerView.LayoutManager lLayout = new GridLayoutManager(this, totalCol);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(new BakeListAdapter(DummyContent.ITEMS));
    }

    public static class BakeListAdapter
            extends RecyclerView.Adapter<BakeListAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) v.getTag();

                Context context = v.getContext();
                Intent intent = new Intent(context, BakeRecipeActivity.class);
                // intent.putExtra(BakeContainerFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }
        };

        public BakeListAdapter(List<DummyContent.DummyItem> mValues) {
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
            holder.mTitle.setText(mValues.get(position).content);
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
