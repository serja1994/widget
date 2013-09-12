package com.example.widget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	final String DIR_SD = "MyFiles";
	final String FILENAME_SD_1 = "fileSD1_new";
	final String FILENAME_SD_2 = "fileSD2_new";
	ArrayList<String> full_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ProgressDialog mProgressDialog;

		// instantiate it within the onCreate method
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setMessage("A message");
		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		mProgressDialog.setCancelable(true);
		DownloadTask downloadTask = new DownloadTask(this);
		downloadTask.execute("http://rp5.ru/xml/6036/ru");

		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	void readFile() {

	
	
		File sdPath = Environment.getExternalStorageDirectory();
		sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
		File sdFile = new File(sdPath, FILENAME_SD_1);
	
		Log.d("myLogs","che kogo");
		try {
			BufferedReader br = new BufferedReader(new FileReader(sdFile));
			String str = new String();
			full_text.clear();
			while ((str = br.readLine()) != null) {
				Log.d("myLogs",str);
			
				full_text.add(str);
		
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	

	}

	
}
