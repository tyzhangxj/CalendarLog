package com.zhuzeyao.calendarlog;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	
	 // ����ExampleAppWidgetService�����Ӧ��action
    private final Intent EXAMPLE_SERVICE_INTENT = 
            new Intent("android.appwidget.action.EXAMPLE_APP_WIDGET_SERVICE");
    // ���� widget �Ĺ㲥��Ӧ��action
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
            // �����¡��㲥

            Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
            //updateAllAppWidgets(context, AppWidgetManager.getInstance(context), idsSet);
        }        
        super.onReceive(context, intent);  
    }    
  
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,  
            int appWidgetId, String titlePrefix) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("M��dd��");
		Date curDate1 = new Date(System.currentTimeMillis());// ��ȡ��ǰ����
		String str = formatter1.format(curDate1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// ��ȡ��ǰʱ��
		String str1 = formatter.format(curDate);
		
		String str2 = null;
		switch(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)){
		case 1:
			str2 = "��";
			break;
		case 2:
			str2 = "һ";
			break;
		case 3:
			str2 = "��";
			break;
		case 4:
			str2 = "��";
			break;
		case 5:
			str2 = "��";
			break;
		case 6:
			str2 = "��";
			break;
		case 7:
			str2 = "��";
			break;
		}
		
		RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.cal);
		views.setTextViewText(R.id.textView1, str); 
		
		RemoteViews views0 = new RemoteViews(context.getPackageName(),R.layout.cal);
		views0.setTextViewText(R.id.textView3, "��"+str2); 
		
		RemoteViews views1 = new RemoteViews(context.getPackageName(),R.layout.cal);
		views1.setTextViewText(R.id.textView2, str1);  
		
        appWidgetManager.updateAppWidget(appid, views); 
        appWidgetManager.updateAppWidget(appid, views0); 
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