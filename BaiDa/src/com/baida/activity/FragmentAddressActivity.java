package com.baida.activity;

import com.baida.adapter.AddressAdapter;
import com.baida.db.AddressManage;
import com.baida.db.DataBaseHelper;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.ListUtil;
import com.baida.widget.FilpperListvew;
import com.baida.widget.FilpperListvew.FilpperDeleteListener;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class FragmentAddressActivity extends SwipeBackActivity  {
	private ImageView button;
	private FilpperListvew listView;
	private AddressAdapter adapter;
private int width;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.baida.swipeback.SwipeBackActivity#onCreate(android.os.Bundle)
	 */
	@SuppressLint("CommitPrefEdits")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_address);
		button = (ImageView) findViewById(R.id.add_address);
		init();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(FragmentAddressActivity.this,
						AddAddressActivity.class);
				startActivity(intent);
			}
		});
		

		setEdgeFromLeft();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		init();
		super.onResume();
	}

	/**
 * 
 */
	private void init() {
		// TODO Auto-generated method stub
		if (new AddressManage().getaddresss(getApplicationContext(), null)
				.size() > 0) {
			listView = (FilpperListvew) findViewById(R.id.address_listview);
			adapter = new AddressAdapter(getApplicationContext(),
					new AddressManage().getaddresss(getApplicationContext(),
							null));
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(FragmentAddressActivity.this,
							AddressDetailActivity.class);
					ListUtil.address = new AddressManage().getaddresss(getApplicationContext(), null);
					intent.putExtra("index", arg2);
					startActivity(intent);
				}
				
			});
			listView.setFilpperDeleteListener(new FilpperDeleteListener() {
				
				@Override
				public void filpperDelete(float xPosition, float yPosition) {
					// listview��Ҫ��item�����򷵻�
					if (listView.getChildCount() == 0)
						return;
					// �������û���ɾ���item��index
					final int index = listView.pointToPosition((int) xPosition,
							(int) yPosition);
					// һ�������ǻ�ø���Ŀ����Ļ��ʾ�е����λ�ã�ֱ�Ӹ��indexɾ����ָ��쳣����Ϊlistview�е�childֻ�е�ǰ����Ļ����ʾ�ĲŲ���Ϊ��
					int firstVisiblePostion = listView.getFirstVisiblePosition();
					View view = listView.getChildAt(index - firstVisiblePostion);

					TranslateAnimation tranAnimation = new TranslateAnimation(0,
							width, 0, 0);
					tranAnimation.setDuration(500);
					tranAnimation.setFillAfter(true);
					view.startAnimation(tranAnimation);
					// ������������Ϻ�ɾ����򲻻���ֶ���Ч���Լ�����ģ���
					tranAnimation.setAnimationListener(new AnimationListener() {
						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							// ɾ��һ��item

							String itemName = new AddressManage().getaddresss(getApplicationContext(), null).get(index).getName();
									
							SQLiteDatabase sqLiteDatabase = DataBaseHelper
									.getInstance(getApplicationContext())
									.getWritableDatabase();
							sqLiteDatabase.execSQL("DELETE FROM " + "AddressList"
									+ " WHERE name = '" + itemName + "'");
							sqLiteDatabase.close();
							DataBaseHelper.closeDB();
							adapter.setAddress(new AddressManage().getaddresss(getApplicationContext(), null));
							adapter.notifyDataSetChanged();
						}
					});

				}
			});
		}
	}
}
