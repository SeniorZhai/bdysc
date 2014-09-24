package com.baida.activity;


import com.baida.swipeback.SwipeBackActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RegisterNormalActivity extends SwipeBackActivity implements
		OnClickListener {
	private Button register_normal = null;
//	private Button register_normal_skip = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_normal);
		findViewById();
		initView();
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
	}

	
	protected void findViewById() {
		// TODO Auto-generated method stub
		register_normal = (Button) findViewById(R.id.register_normal_btn);
//		register_normal_skip = (Button) findViewById(R.id.register_normal_skip_btn);
	}


	protected void initView() {
		register_normal.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.register_normal_btn:
			gotohome();
			break;
//		case R.id.register_normal_skip_btn:
//			gotohome();
//			break;
		default:
			break;
		}

	}
	private void gotohome() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(RegisterNormalActivity.this, MainActivity.class);
		// intent.setClass(getApplicationContext(),RegisterActivity.class);
		startActivity(intent);
		RegisterNormalActivity.this.finish();
	}

}
