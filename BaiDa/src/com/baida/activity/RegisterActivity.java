package com.baida.activity;

import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.CommonTools;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends SwipeBackActivity {

	private EditText mobile;
	private String registerNum;
	private ImageButton checkBox;
	private Button access_password;
	private	Button register_mormal;
	private boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		findViewById();
		initView();
	}


	protected void findViewById() {
		mobile = (EditText) this.findViewById(R.id.edit_mobile);
		checkBox = (ImageButton) this.findViewById(R.id.checkBox);
		access_password = (Button) this.findViewById(R.id.access_password);
		register_mormal = (Button) this.findViewById(R.id.register_mormal);

	}


	protected void initView() {
		new CommonTools();
		registerNum = mobile.getText().toString();
		// 判断是否是手机
		CommonTools.isMobileNO(registerNum);
		if (flag == false) {
//			DisPlay("您输入的手机号不合法");
		}

		checkBox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (flag == false) {
					access_password.setTextColor(Color.BLACK);
					flag = true;
				}

				else {
					access_password.setTextColor(Color.WHITE);
				}

			}
		});
		
		/**
		 * 跳转到普通注册*/
		register_mormal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(RegisterActivity.this, RegisterNormalActivity.class));	
			}
		});

	}

}
