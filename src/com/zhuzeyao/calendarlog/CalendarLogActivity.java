package com.zhuzeyao.calendarlog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarLogActivity extends Activity {
    /** Called when the activity is first created. */
	Button btn;
	private final static Intent EXAMPLE_SERVICE_INTENT = new Intent(
			"android.appwidget.action.EXAMPLE_APP_WIDGET_SERVICE");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        btn = (Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				CalendarLogActivity.this.startService(EXAMPLE_SERVICE_INTENT);
			}
		});
    }
}