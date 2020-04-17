package com.manitos.dev.gilinhobakingapp.features.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;

import com.manitos.dev.gilinhobakingapp.R;
import com.manitos.dev.gilinhobakingapp.features.home.BakeListActivity;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link BakeAppWidgetConfigureActivity BakeAppWidgetConfigureActivity}
 */
public class BakeAppWidget extends AppWidgetProvider {

    static void updateAppWidget(
            Context context,
            AppWidgetManager appWidgetManager,
            int appWidgetId
    ) {
        CharSequence widgetText = BakeAppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bake_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        setRemoteAdapter(context, views);

        // When we click the widget, we want to open our main activity.
        Intent launchActivity = new Intent(context, BakeListActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, launchActivity, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);;

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            BakeAppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static void setRemoteAdapter(Context context, @NonNull final RemoteViews views) {
        views.setRemoteAdapter(
                R.id.widget_list,
                new Intent(context, BakeAppWidgetService.class)
        );
    }
}

