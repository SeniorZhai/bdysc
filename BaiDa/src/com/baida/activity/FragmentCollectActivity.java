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
				// listview中要有item，否则返回
				if (listView.getChildCount() == 0)
					return;
				// 根据坐标获得滑动删除的item的index
				final int index = listView.pointToPosition((int) xPosition,
						(int) yPosition);
				// 一下两步是获得该条目在屏幕显示中的相对位置，直接根据index删除会空指針异常。因为listview中的child只有当前在屏幕中显示的才不会为空
				int firstVisiblePostion = listView.getFirstVisiblePosition();
				View view = listView.getChildAt(index - firstVisiblePostion);

				TranslateAnimation tranAnimation = new TranslateAnimation(0,
						width, 0, 0);
				tranAnimation.setDuration(500);
				tranAnimation.setFillAfter(true);
				view.startAnimation(tranAnimation);
				// 当动画播放完毕后，删除。否则不会出现动画效果（自己试验的）。
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
						// 删除一个item

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
