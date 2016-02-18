package com.zhuzeyao.calendarlog;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

public class CalendarLogWidgetProvider extends AppWidgetProvider {
	@Override  
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {  
    }  
      
    @Override  
    public void onDeleted(Context context, int[] appWidgetIds) {   
    }  
  
    @Override  
    public void onEnabled(Context context) {   
    }  
  
    @Override  
    public void onDisabled(Context context) {   
    }  
  
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,  
            int appWidgetId, String titlePrefix) {   
    }  

}