package com.data.activity;

import com.baida.service.DataService;
import com.baida.service.LoginService;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imageView;
	private EditText code;
	private String codeString;
	private Button button;
	private Button login;
	private Button data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.imageView1);
		code = (EditText) findViewById(R.id.code);
		button = (Button) findViewById(R.id.btn);
		login = (Button) findViewById(R.id.login);
		data = (Button) findViewById(R.id.data);
		new FirstAsyncTask().execute("");
	
		data.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DataAsyncTask().execute("");
			}
		});
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new LoginAsyncTask().execute("");
			}
		});
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				new MyAsyncTask().execute("");
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class MyAsyncTask extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			codeString = code.getText().toString();
		
		
			Bitmap bitmap = new LoginService()
					.getBitmap("http://www.bdysc.com/ValidateCode?dumy=0.5301157233770937");

			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			imageView.setImageBitmap(result);
		}
	}

	public class LoginAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			codeString = code.getText().toString();
			new LoginService().loginStudy("sundroid", "123456wo", codeString);

			return null;
		}
	}

	public class FirstAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {

			new LoginService().first();
			return null;
		}
	}
	public class DataAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected String doInBackground(String... params) {

			new DataService().getData();
			return null;
		}
	}
}
