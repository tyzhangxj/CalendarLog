package com.zhuzeyao.calendarlog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.app.PendingIntent;
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
	public static final String ACTION_TIME_TICK = "android.intent.action.TIME_TICK";
	public static final String ACTION_BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";

	// 启动ExampleAppWidgetService服务对应的action
	private final static Intent EXAMPLE_SERVICE_INTENT = new Intent(
			"android.appwidget.action.EXAMPLE_APP_WIDGET_SERVICE");
	// 更新 widget 的广播对应的action
	private final String ACTION_UPDATE_ALL = "com.zhuzeyao.calendarlog.UPDATE_ALL";

	TimeBroadcastReceiver TBR;
	BatteryBroadcastReceiver BBR;
	static int appid;
	private static Set idsSet = new HashSet();
	private static Intent fillInIntent;

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			appid = appWidgetId;
			idsSet.add(Integer.valueOf(appWidgetId));
			Toast.makeText(context, "onUpdate" + appid, Toast.LENGTH_LONG)
					.show();
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
		context.getApplicationContext().registerReceiver(TBR,
				new IntentFilter(ACTION_UPDATE_ALL));
		// context.getApplicationContext().registerReceiver(TBR, new
		// IntentFilter(ACTION_TIME_TICK));
		// context.getApplicationContext().registerReceiver(BBR, new
		// IntentFilter(ACTION_BATTERY_CHANGED));
		Toast.makeText(context, "enabled", Toast.LENGTH_LONG).show();

	}

	@Override
	public void onDisabled(Context context) {
		context.stopService(EXAMPLE_SERVICE_INTENT);
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		final String action = intent.getAction();
		// Toast.makeText(context, action, Toast.LENGTH_LONG).show();
		if (ACTION_TIME_TICK.equals(action)) {
			// “更新”广播

			Toast.makeText(context, "onReceive", Toast.LENGTH_LONG).show();
			// updateAllAppWidgets(context,
			// AppWidgetManager.getInstance(context), idsSet);
		}
		super.onReceive(context, intent);
	}

	static void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			String titlePrefix) {
		SimpleDateFormat formatter1 = new SimpleDateFormat("M月dd日");
		Date curDate1 = new Date(System.currentTimeMillis());// 获取当前日期
		String str = formatter1.format(curDate1);

		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str1 = formatter.format(curDate);

		String str2 = null;
		switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
		case 1:
			str2 = "日";
			break;
		case 2:
			str2 = "一";
			break;
		case 3:
			str2 = "二";
			break;
		case 4:
			str2 = "三";
			break;
		case 5:
			str2 = "四";
			break;
		case 6:
			str2 = "五";
			break;
		case 7:
			str2 = "六";
			break;
		}

		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.cal);
		views.setTextViewText(R.id.textView1, str);
		views.setOnClickPendingIntent(R.id.linearLayout1, getPendingIntent(context));

		RemoteViews views0 = new RemoteViews(context.getPackageName(),
				R.layout.cal);
		views0.setTextViewText(R.id.textView3, "周" + str2);

		RemoteViews views1 = new RemoteViews(context.getPackageName(),
				R.layout.cal);
		views1.setTextViewText(R.id.textView2, str1);

		appWidgetManager.updateAppWidget(appWidgetId, views);
		appWidgetManager.updateAppWidget(appWidgetId, views0);
		appWidgetManager.updateAppWidget(appWidgetId, views1);
	}

	private static PendingIntent getPendingIntent(Context context) {
		
		Intent intent = new Intent();
		intent.setClass(context, CalendarLogActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
		return pi;
	}

	public class TimeBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// widget 的id
			int appID;
			// 迭代器，用于遍历所有保存的widget的id
			Iterator it = idsSet.iterator();

			while (it.hasNext()) {
				appID = ((Integer) it.next()).intValue();
				updateAppWidget(context, AppWidgetManager.getInstance(context),
						appID, "");
			}
		}

	}

	public class BatteryBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(context, "aaa", Toast.LENGTH_LONG).show();
		}
	}
}