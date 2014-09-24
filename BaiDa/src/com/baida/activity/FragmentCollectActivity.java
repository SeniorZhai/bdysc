package com.baida.activity;

import com.baida.adapter.CollectAdapter;
import com.baida.db.CollectManage;
import com.baida.db.DataBaseHelper;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.ListUtil;
import com.baida.widget.FilpperListvew;
import com.baida.widget.FilpperListvew.FilpperDeleteListener;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FragmentCollectActivity extends SwipeBackActivity {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.baida.swipeback.SwipeBackActivity#onCreate(android.os.Bundle)
	 */
	private FilpperListvew listView;
	CollectAdapter adapter;
	private int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_collect);
		ListUtil.collects = new CollectManage().getProducts(
				this, null);
		adapter = new CollectAdapter(this, ListUtil.collects);
		listView = (FilpperListvew) findViewById(R.id.clloect_listview);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FragmentCollectActivity.this,
						CollectDetailsActivity.class);
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
				// ���������û���ɾ����item��index
				final int index = listView.pointToPosition((int) xPosition,
						(int) yPosition);
				// һ�������ǻ�ø���Ŀ����Ļ��ʾ�е����λ�ã�ֱ�Ӹ���indexɾ�����ָ��쳣����Ϊlistview�е�childֻ�е�ǰ����Ļ����ʾ�ĲŲ���Ϊ��
				int firstVisiblePostion = listView.getFirstVisiblePosition();
				View view = listView.getChildAt(index - firstVisiblePostion);

				TranslateAnimation tranAnimation = new TranslateAnimation(0,
						width, 0, 0);
				tranAnimation.setDuration(500);
				tranAnimation.setFillAfter(true);
				view.startAnimation(tranAnimation);
				// ������������Ϻ�ɾ�������򲻻���ֶ���Ч�����Լ�����ģ���
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

						String itemName = new CollectManage()
								.getProducts(getApplicationContext(), null)
								.get(index).getProductName();
						SQLiteDatabase sqLiteDatabase = DataBaseHelper
								.getInstance(getApplicationContext())
								.getWritableDatabase();
						sqLiteDatabase.execSQL("DELETE FROM " + "CollectList"
								+ " WHERE productName = '" + itemName + "'");

						sqLiteDatabase.close();
						DataBaseHelper.closeDB();
						adapter.setProducts(new CollectManage().getProducts(
								getApplicationContext(), null));
						adapter.notifyDataSetChanged();

					}
				});

			}
		});
		setEdgeFromLeft();
	}
}
