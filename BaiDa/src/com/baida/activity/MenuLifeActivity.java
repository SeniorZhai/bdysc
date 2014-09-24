package com.baida.activity;

import com.baida.adapter.MenuViewAdapter;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.widget.MyGridView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MenuLifeActivity extends SwipeBackActivity {

	private MyGridView menu_life_gridview;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_life);
		menu_life_gridview = (MyGridView) findViewById(R.id.menu_life_gridview);
		int[] images = new int[] { R.drawable.t5, R.drawable.t6, R.drawable.t7,
				R.drawable.t8, R.drawable.t9 };
		menu_life_gridview.setAdapter(new MenuViewAdapter(this, images));
		menu_life_gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					intent = new Intent();
					intent.setClass(MenuLifeActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 55);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent();
					intent.setClass(MenuLifeActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 55);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent();
					intent.setClass(MenuLifeActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 55);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent();
					intent.setClass(MenuLifeActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 55);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent();
					intent.setClass(MenuLifeActivity.this,
							ProductListActivity.class);
					intent.putExtra("position", arg2 + 55);
					startActivity(intent);
					break;

				default:
					break;
				}

			}
		});
	}

}
