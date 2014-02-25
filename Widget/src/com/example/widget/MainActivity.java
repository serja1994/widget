package com.example.widget;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	final String CITY = "http://informer.gismeteo.ru/xml/34601_1.xml";
	DownloadTask downloadTask;
	ListView lvMain;
	Button b;
	Parser ps;
	ArrayList<DataToList> dataToList;
	ArrayList<String> dataFromGismeteo;
	TextView txt;
	Adapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvMain = (ListView) findViewById(R.id.listView1);
		downloadTask = new DownloadTask(this);
		txt = (TextView) findViewById(R.id.textView1);
		b = (Button) findViewById(R.id.button1);
		ps = new Parser();
		dataToList = new ArrayList<DataToList>();

		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (isOnline(arg0.getContext())) {

					downloadTask = (DownloadTask) new DownloadTask(arg0
							.getContext()).execute(CITY);
				} else {
					Toast.makeText(arg0.getContext(),
							"Отсутствует соединение с интернетом",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		lvMain.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				try {
					dataFromGismeteo = downloadTask.get();

				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					txt.setText("Нет данных");
					e.printStackTrace();

				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent(arg0.getContext(), Activity2.class);
				intent.putExtra("str", dataFromGismeteo);
				intent.putExtra("position", arg2);
				startActivity(intent);

			}
		});

	}

	class DownloadTask extends AsyncTask<String, Integer, ArrayList<String>> {
		ArrayList<String> str = new ArrayList<String>();
		private Context context;
		ProgressDialog mProgressDialog;

		public DownloadTask(Context context) {
			this.context = context;
		}

		@Override
		protected ArrayList<String> doInBackground(String... sUrl) {

			InputStream input = null;
			HttpURLConnection connection = null;
			try {
				URL url = new URL(sUrl[0]);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {

					return null;
				}

				input = connection.getInputStream();
				BufferedReader r = new BufferedReader(new InputStreamReader(
						input));

				String line;
				while ((line = r.readLine()) != null) {
					str.add(line);

				}

			} catch (Exception e) {
				return null;
			} finally {
				if (connection != null)
					connection.disconnect();
			}

			return str;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected void onProgressUpdate(Integer... progress) {
			super.onProgressUpdate(progress);

		}

		protected void onPostExecute(ArrayList<String> result) {
			dataToList.clear();
			txt.setText("Данные взяты с сайта gismeteo: " + CITY);
			try {
				ps.parserLow(result, dataToList);
			} catch (NullPointerException e) {
				Toast.makeText(context, "Не удалось подключиться к серверу",
						Toast.LENGTH_SHORT).show();
				txt.setText("Нет данных");
				e.printStackTrace();
			}
			adapter = new Adapter(context, dataToList, 0);
			lvMain.setAdapter(adapter);

		}
	}

	public class Adapter extends BaseAdapter {
		Context ctx;
		int positionz, kol;
		String proba = null;
		LayoutInflater lInflater;
		ArrayList<DataToList> objects;

		Adapter(Context context, ArrayList<DataToList> products, int pos) {
			ctx = context;
			positionz = pos;
			objects = products;
			lInflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		}

		@Override
		public int getCount() {
			return objects.size();
		}

		@Override
		public Object getItem(int position) {
			return objects.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = lInflater.inflate(R.layout.list, lvMain, false);
			}

			DataToList p = getProduct(position);
			((TextView) view.findViewById(R.id.list_textView1))
					.setText(p.first);
			((TextView) view.findViewById(R.id.list_textView2))
					.setText(p.second);

			((TextView) view.findViewById(R.id.list_textView3))
					.setText(p.third);

			return view;
		}

		DataToList getProduct(int position) {
			return ((DataToList) getItem(position));
		}
	}

	public static boolean isOnline(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}
}
