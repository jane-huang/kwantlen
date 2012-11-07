package com.example.wiget.info;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {

  private static final String ACTION_CLICK = "ACTION_CLICK";

  public static String WIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
  public void onReceive(Context context, Intent intent)
  {
	  super.onReceive( context,  intent);
	  if(WIDGET_UPDATE.equals(intent.getAction()))
	  {
		  //
		  String a = "whatever";
		  
	  }
	  
  }
  
  @Override
  public void onUpdate(Context context, AppWidgetManager appWidgetManager,
      int[] appWidgetIds) {

	  super.onUpdate(context, appWidgetManager, appWidgetIds);
    // Get all ids
    ComponentName thisWidget = new ComponentName(context,
        MyWidgetProvider.class);
    int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
    for (int widgetId : allWidgetIds) {
      // Create some random data
      int number = (new Random().nextInt(100));

      RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
          R.layout.widget_layout);
      Log.w("WidgetExample", String.valueOf(number));
      
      // Set the text
      DateFormat format = SimpleDateFormat.getTimeInstance( SimpleDateFormat.MEDIUM, Locale.getDefault() );
      remoteViews.setTextViewText(R.id.update, format.format( new Date()));
      // Register an onClickListener
      Intent intent = new Intent(context, MyWidgetProvider.class);

      intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
      intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

      PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
          0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
      remoteViews.setOnClickPendingIntent(R.id.update, pendingIntent);  
      
      appWidgetManager.updateAppWidget(widgetId, remoteViews);
    }
  }
} 