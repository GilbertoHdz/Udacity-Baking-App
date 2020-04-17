package com.manitos.dev.gilinhobakingapp.features.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.manitos.dev.gilinhobakingapp.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.text1;
import static android.R.layout.simple_list_item_1;

public class BakeAppWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakeAppWidgetViewsFactory(this.getApplicationContext());
    }
}

class BakeAppWidgetViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    List<String> myListView = new ArrayList<>();
    Context mContext;

    public BakeAppWidgetViewsFactory(Context applicationContext) {
        this.mContext = applicationContext;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return myListView.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                simple_list_item_1);
        view.setTextViewText(text1, myListView.get(position));

        // Fill in the onClick PendingIntent Template using the specific plant Id for each item individually
        Bundle extras = new Bundle();
        extras.putLong("ID_BAKE_WIDGET", position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        view.setOnClickFillInIntent(R.id.appwidget_text, fillInIntent);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        myListView.clear();
        for (int i = 1; i <= 15; i++) {
            myListView.add("ListView item " + i);
        }
    }
}
