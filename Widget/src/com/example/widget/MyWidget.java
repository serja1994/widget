package com.example.widget;


import java.util.Arrays;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MyWidget extends AppWidgetProvider {

	String today_1[] = { "В Новосибирске", "2013-9-17 07:00", "92", "0.2",
			"750", "3", "90", "С", "1", "1", "0.5" };
	String today_2[] = { "В Новосибирске", "2013-9-17 19:00", "85", "0.8",
			"750", "8", "58", "З", "2", "1", "1" };

	String tomorrow_1[] = { "В Новосибирске", "2013-9-17 07:00", "92", "0",
			"753", "2", "91", "Ю-В", "1", "0", "0" };
	String tomorrow_2[] = { "В Новосибирске", "2013-9-17 19:00", "85", "0.9",
			"753", "6", "60", "З", "2", "1", "1" };

	TextView tv1 = (TextView) findViewById(R.id.textView1);
	TextView tv2 = (TextView) findViewById(R.id.textView2);
	TextView tv3 = (TextView) findViewById(R.id.textView3);
	TextView tv4 = (TextView) findViewById(R.id.textView4);
	TextView tv5 = (TextView) findViewById(R.id.textView5);
	TextView tv6 = (TextView) findViewById(R.id.textView6);
	TextView tv7 = (TextView) findViewById(R.id.textView7);
	TextView tv8 = (TextView) findViewById(R.id.textView8);
	TextView tv9 = (TextView) findViewById(R.id.textView9);
	
	// ImageView iv1 = (ImageView) findViewById(R.id.imageView1);
	
	String stv1;// = tv1.getText().toString();
	String stv2;// = tv2.getText().toString();
	String stv3;// = tv3.getText().toString();
	String stv4;// = tv4.getText().toString();
	String stv5;// = tv5.getText().toString();
	String stv6;// = tv6.getText().toString();
	String stv7;// = tv7.getText().toString();
	String stv8;// = tv8.getText().toString();
	String stv9;// = tv9.getText().toString();

	final String LOG_TAG = "myLogs";

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
		Log.d(LOG_TAG, "onEfffnabled");
		
		tv1 = (TextView) findViewById(R.id.textView1);
		tv1.setText("123");

		Log.d(LOG_TAG, "onEnabledEnd");

	}

	private TextView findViewById(int textview1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.d(LOG_TAG, "onUpdate " + Arrays.toString(appWidgetIds));

		tv1.setText("123");
		tv1.setText(stv1 + " " + today_1[0]);

		tv2.setText(stv2 + " " + today_1[1] + " " + today_2[1] + " "
				+ tomorrow_1[1] + " " + tomorrow_2[1]);

		tv5.setText(stv5 + " " + today_1[4] + " " + today_2[4] + " "
				+ tomorrow_1[4] + " " + tomorrow_2[4]);
		tv6.setText(stv6 + " " + today_1[5] + " " + today_2[5] + " "
				+ tomorrow_1[5] + " " + tomorrow_2[5]);
		tv7.setText(stv7 + " " + today_1[6] + " " + today_2[6] + " "
				+ tomorrow_1[6] + " " + tomorrow_2[6]);
		tv8.setText(stv8 + " " + today_1[7] + " " + today_2[7] + " "
				+ tomorrow_1[7] + " " + tomorrow_2[7]);
		tv9.setText(stv9 + " " + today_1[8] + " " + today_2[8] + " "
				+ tomorrow_1[8] + " " + tomorrow_2[8]);

		Log.d(LOG_TAG, "onUpdateEnd");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
		Log.d(LOG_TAG, "onDeleted " + Arrays.toString(appWidgetIds));
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
		Log.d(LOG_TAG, "onDisabled");
	}

}