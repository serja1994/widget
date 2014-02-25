package com.example.gismeteo;

import java.util.ArrayList;

import com.example.widget.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity2 extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list2);
		Intent intent = getIntent();
	
		Parser ps = new Parser();

		ArrayList<String> str = intent.getStringArrayListExtra("str");
		int k = intent.getIntExtra("position", 0);
		ps.parserHigh(str, k);
	
		((TextView) findViewById(R.id.list2_textView1)).setText(str.get(0));
		((TextView) findViewById(R.id.list2_textView2)).setText(str.get(1));
		((TextView) findViewById(R.id.list2_textView3)).setText(str.get(2));
		((TextView) findViewById(R.id.list2_textView4)).setText(str.get(3));
		((TextView) findViewById(R.id.list2_textView5)).setText(str.get(4));
		((TextView) findViewById(R.id.list2_textView6)).setText(str.get(5));
		((TextView) findViewById(R.id.list2_textView7)).setText(str.get(6));
	}
}
