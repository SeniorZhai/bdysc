package com.baida.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.baida.domain.Address;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.ListUtil;

public class AddressDetailActivity extends SwipeBackActivity {
	private EditText detail_address_address;
	private EditText detail_address_name;
	private EditText detail_address_phone;
	private EditText detail_address_street;
	private Button detail_address_btn;
	Address address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_detail);
		int index = getIntent().getIntExtra("index", 0);
		address = ListUtil.address.get(index);
		init();
		detail_address_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	private void init() {
		// TODO Auto-generated method stub
		detail_address_address = (EditText) findViewById(R.id.detail_address_address);
		detail_address_address.setText(address.getAddress());
		detail_address_name = (EditText) findViewById(R.id.detail_address_name);
		detail_address_name.setText(address.getName());
		detail_address_phone = (EditText) findViewById(R.id.detail_address_phone);
		detail_address_phone.setText(address.getPhone());
		detail_address_street = (EditText) findViewById(R.id.detail_address_street);
		detail_address_street.setText(address.getJiedao());
		detail_address_btn = (Button) findViewById(R.id.detail_address_btn);
	}

}
