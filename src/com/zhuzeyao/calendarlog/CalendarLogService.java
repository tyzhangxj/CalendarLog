package com.zhuzeyao.calendarlog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class CalendarLogService extends Service {
	
	private final String ACTION_UPDATE_ALL = "com.zhuzeyao.calendarlog.UPDATE_ALL";
	private UpdateThread mUpdateThread;
	private Context mContext;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
    public void onCreate() {
        
        // �����������߳�UpdateThread
        mUpdateThread = new UpdateThread();
        mUpdateThread.start();
        
        mContext = this.getApplicationContext();

        super.onCreate();
    }
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        
        return START_STICKY;
    }
	@Override
	public void onDestroy() {
		if (mUpdateThread != null) {
            mUpdateThread.interrupt();
        }
		super.onDestroy();
	}

	private class UpdateThread extends Thread {

		@Override
		public void run() {
			super.run();

			try { 
				while (true) {  

					Intent updateIntent = new Intent(ACTION_UPDATE_ALL);
					mContext.sendBroadcast(updateIntent);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				// �� InterruptedException ������whileѭ��֮�⣬��ζ���׳�
				// InterruptedException �쳣ʱ����ֹ�̡߳�
				e.printStackTrace();
			}
		}
	}
}
