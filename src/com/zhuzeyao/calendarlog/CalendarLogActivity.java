package com.zhuzeyao.calendarlog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarLogActivity extends Activity {
    /** Called when the activity is first created. */
	Button btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}