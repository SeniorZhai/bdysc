package com.baida.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.baida.config.ConstantS;
import com.baida.fragment.FragmentIndicator;
import com.baida.fragment.FragmentIndicator.OnIndicateListener;
import com.baida.slidingmenu.lib.SlidingMenu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements OnClickListener {
	public static Fragment[] mFragments;
	private boolean isExit = false;
	private TimerTask timeTask = null;
	private SharedPreferences settings = null;
	private Timer timer = null;
	private SlidingMenu slidingMenu = null;
    private Intent intent;
	private View view;
	private RelativeLayout food_layout, clean_layout, day_layout,
			mother_layout, beauty_layout, life_layout, service_layout;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		timer = new Timer();

		

		slidingMenu = new SlidingMenu(this);

		slidingMenu.setMode(SlidingMenu.LEFT);

		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN); // 触摸边界拖出菜单

		slidingMenu.setClickable(true);

		slidingMenu.setMenu(R.layout.menu_sliding_left);

		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 将抽屉菜单与主页面关联起来
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		
		view = slidingMenu.getMenu();
		
		initview();

		settings = getSharedPreferences(ConstantS.PREFS_NAME, 0);
		String UserName = settings.getString("UserName", null);
		if (UserName == null) {
			ImageView usericon = (ImageView) findViewById(R.id.fragment_dsx_pic);
			usericon.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							LoginActivity.class);
					startActivity(intent);
				}
			});
		}
		setFragmentIndicator(0);
	}

	private void setFragmentIndicator(int whichIsDefault) {
		mFragments = new Fragment[4];
		mFragments[0] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_home);
		mFragments[1] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_buy);
		mFragments[2] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_shopping);
		mFragments[3] = getSupportFragmentManager().findFragmentById(
				R.id.fragment_user);

		getSupportFragmentManager().beginTransaction().hide(mFragments[0])
				.hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3])
				.show(mFragments[whichIsDefault]).commit();

		FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);
		FragmentIndicator.setIndicator(whichIsDefault);
		mIndicator.setOnIndicateListener(new OnIndicateListener() {
			@Override
			public void onIndicate(View v, int which) {
				getSupportFragmentManager().beginTransaction()
						.hide(mFragments[0]).hide(mFragments[1])
						.hide(mFragments[2]).hide(mFragments[3])
						.show(mFragments[which]).commit();

			}
		});

	}

	@Override
	public void onBackPressed() {
		if (isExit) {
			finish();
		} else {
			isExit = true;
			Toast.makeText(MainActivity.this, "再按一次退出百大易商城", Toast.LENGTH_SHORT)
					.show();
			timeTask = new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			};
			timer.schedule(timeTask, 2000);
		}
	}

	private void initview() {

		food_layout = (RelativeLayout) view.findViewById(R.id.food_layout);
		food_layout.setOnClickListener(this);

		clean_layout = (RelativeLayout) view.findViewById(R.id.clean_layout);
		clean_layout.setOnClickListener(this);

		day_layout = (RelativeLayout) view.findViewById(R.id.day_layout);
		day_layout.setOnClickListener(this);

		mother_layout = (RelativeLayout) view.findViewById(R.id.mother_layout);
		mother_layout.setOnClickListener(this);

		beauty_layout = (RelativeLayout) view.findViewById(R.id.beauty_layout);
		beauty_layout.setOnClickListener(this);

		life_layout = (RelativeLayout) view.findViewById(R.id.life_layout);
		life_layout.setOnClickListener(this);

		service_layout = (RelativeLayout) view
				.findViewById(R.id.service_layout);
		service_layout.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.food_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuFoodActivity.class);
			startActivity(intent);
			break;
		case R.id.clean_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuCleanActivity.class);
			startActivity(intent);
			break;
		case R.id.day_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuDayActivity.class);
			startActivity(intent);

			break;
		case R.id.mother_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuMotherActivity.class);
			startActivity(intent);

			break;
		case R.id.beauty_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuBeautyActivity.class);
			startActivity(intent);

			break;
		case R.id.life_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuLifeActivity.class);
			startActivity(intent);


			break;
		case R.id.service_layout:
			intent = new Intent();
			intent.setClass(MainActivity.this, MenuServiceActivity.class);
			startActivity(intent);

			break;

		default:
			break;
		}

	}
}
