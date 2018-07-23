package com.wojdor.bakingapp.application.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.wojdor.bakingapp.R;
import com.wojdor.bakingapp.application.splash.SplashActivity;

public class RecipeWidgetProvider extends AppWidgetProvider {

    private static final int REQUEST_CODE = 0;
    private static final int DEFAULT_FLAG = 0;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_recipe);
        views.setTextViewText(R.id.appwidget_text, context.getString(R.string.appwidget_text));
        views.setOnClickPendingIntent(R.id.widget_recipe_container_rv, createOnClickPendingIntent(context));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private PendingIntent createOnClickPendingIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return PendingIntent.getActivity(context, REQUEST_CODE, intent, DEFAULT_FLAG);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

