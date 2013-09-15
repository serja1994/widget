package com.example.widget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	final String DIR_SD = "MyFiles";
	final String FILENAME_SD_1 = "fileSD1_new";
	final String FILENAME_SD_2 = "fileSD2_new";

	ArrayList<String> full_text;
	ArrayList<String> full_text2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// instantiate it within the onCreate method

	
		full_text = new ArrayList<String>();
		full_text2 = new ArrayList<String>();
		DownloadTask downloadTask = new DownloadTask(this);
		downloadTask.execute("http://rp5.ru/xml/6036/ru");

		readFile();
		peredelivaem();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// читаем в full_text из файла
	void readFile() {

		File sdPath = Environment.getExternalStorageDirectory();
		sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
		File sdFile = new File(sdPath, FILENAME_SD_1);
		// TextView txt2=(TextView) findViewById(R.id.textView1);

		try {
			BufferedReader br = new BufferedReader(new FileReader(sdFile));
			String str = new String();
			// txt2.setText("");
			while ((str = br.readLine()) != null) {
				// txt2.setText(txt2.getText().toString() + str + '\n');
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

	// сдесь мы вибираем из full_text нужные нам тэги в full_text2, а потом
	// возавращаем нужные в full_text
	void peredelivaem() {
		full_text2.add(full_text.get(6));

		full_text2.add(full_text.get(15));
		full_text2.add(full_text.get(18));
		full_text2.add(full_text.get(19));
		full_text2.add(full_text.get(20));
		full_text2.add(full_text.get(21));
		full_text2.add(full_text.get(22));
		full_text2.add(full_text.get(23));
		full_text2.add(full_text.get(24));
		full_text2.add(full_text.get(25));
		full_text2.add(full_text.get(26));

		full_text2.add(full_text.get(30));
		full_text2.add(full_text.get(33));
		full_text2.add(full_text.get(34));
		full_text2.add(full_text.get(35));
		full_text2.add(full_text.get(36));
		full_text2.add(full_text.get(37));
		full_text2.add(full_text.get(38));
		full_text2.add(full_text.get(39));
		full_text2.add(full_text.get(40));
		full_text2.add(full_text.get(41));

		full_text2.add(full_text.get(45));
		full_text2.add(full_text.get(48));
		full_text2.add(full_text.get(49));
		full_text2.add(full_text.get(50));
		full_text2.add(full_text.get(51));
		full_text2.add(full_text.get(52));
		full_text2.add(full_text.get(53));
		full_text2.add(full_text.get(54));
		full_text2.add(full_text.get(55));
		full_text2.add(full_text.get(56));

		full_text2.add(full_text.get(60));
		full_text2.add(full_text.get(63));
		full_text2.add(full_text.get(64));
		full_text2.add(full_text.get(65));
		full_text2.add(full_text.get(66));
		full_text2.add(full_text.get(67));
		full_text2.add(full_text.get(68));
		full_text2.add(full_text.get(69));
		full_text2.add(full_text.get(70));
		full_text2.add(full_text.get(71));
		full_text.clear();
		for (int i = 0; i < full_text2.size(); i++) {
			full_text.add(reboot(full_text2.get(i)));

		}
	}

	// сдесь убираются тэги типа <> </>
	String reboot(String s) {
		String k = "";
		char ss[] = s.toCharArray();

		for (int i = 1 + s.indexOf('>'); i < ss.length; i++) {
			if (ss[i] == '<')
				break;
			{
				k = k + ss[i];
			}
		}
		return k;
	}

}
