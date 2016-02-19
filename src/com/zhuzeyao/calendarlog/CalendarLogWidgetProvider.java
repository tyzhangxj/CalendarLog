package com.zhuzeyao.calendarlog;
 
import java.text.SimpleDateFormat;
import java.util.Date; 
 

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.RemoteViews;
import android.widget.Toast;

public class CalendarLogWidgetProvider extends AppWidgetProvider {
	public static final String ACTION_TIME_TICK = 
			"android.intent.action.TIME_TICK";
	public static final String ACTION_BATTERY_CHANGED = 
			"android.intent.action.BATTERY_CHANGED";
	
	 // 启动ExampleAppWidgetService服务对应的action
    private final Intent EXAMPLE_SERVICE_INTENT = 
            new Intent("android.appwidget.action.EXAMPLE_APP_WIDGET_SERVICE");
    // 更新 widget 的广播对应的action
    private final String ACTION_UPDATE_ALL = "com.zhuzeyao.calendarlog.UPDATE_ALL";
    
	TimeBroadcastReceiver TBR;
	BatteryBroadcastReceiver BBR;
	static int appid;
	@Override  
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {  
		for (int appWidgetId : appWidgetIds) {
			appid = appWidgetId;
	    	Toast.makeText(context, "onUpdate"+appid, Toast.LENGTH_LONG).show();
        }
    }  
      
    @Override  
    public void onDeleted(Context context, int[] appWidgetIds) {   
    	Toast.makeText(context, "onDeleted", Toast.LENGTH_LONG).show();
    }  
  
    @Override  
    public void onEnabled(Context context) {   
    	context.startService(EXAMPLE_SERVICE_INTENT);
    	TBR = new TimeBroadcastReceiver();
    	BBR = new BatteryBroadcastReceiver();
    	context.getApplicationContext().registerReceiver(TBR, new IntentFilter(ACTION_UPDATE_ALL));
    	//context.getApplicationContext().registerReceiver(TBR, new IntentFilter(ACTION_TIME_TICK));
    	//context.getApplicationContext().registerReceiver(BBR, new IntentFilter(ACTION_BATTERY_CHANGED));
    	Toast.makeText(context, "enabled", Toast.LENGTH_LONG).show();
    	
    }  
  
    @Override  
    public void onDisabled(Context context) {   
    	context.stopService(EXAMPLE_SERVICE_INTENT);
    } 
    @Override  
    public void onReceive(Context context, Intent intent) {  

        final String action = intent.getAction(); 
        //Toast.makeText(context, action, Toast.LENGTH_LONG).show();
        if (ACTION_TIME_TICK.equals(action)) {
            // “更新”广播

            Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
            //updateAllAppWidgets(context, AppWidgetManager.getInstance(context), idsSet);
        }        
        super.onReceive(context, intent);  
    }    
  
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,  
            int appWidgetId, String titlePrefix) {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat formatter1 = new SimpleDateFormat("M月dd日");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		Date curDate1 = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter1.format(curDate1);

		String str1 = formatter.format(curDate);
		RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.cal);
		views.setTextViewText(R.id.textView1, str); 
		RemoteViews views1 = new RemoteViews(context.getPackageName(),R.layout.cal);
		views1.setTextViewText(R.id.textView2, str1);  
        appWidgetManager.updateAppWidget(appid, views); 
        appWidgetManager.updateAppWidget(appid, views1); 
    }  
    public class TimeBroadcastReceiver extends BroadcastReceiver{

    	@Override
    	public void onReceive(Context context, Intent intent) {  
    		updateAppWidget(context, AppWidgetManager.getInstance(context), appid,"");  
    	}

    }
    public class BatteryBroadcastReceiver extends BroadcastReceiver{ 
      	 
    	@Override
    	public void onReceive(Context context, Intent intent) { 
    		Toast.makeText(context, "aaa", Toast.LENGTH_LONG).show();
    	}
    }
}