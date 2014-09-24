package com.baida.adapter;

import java.util.HashMap;
import java.util.Map;

import com.baida.activity.R;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

public class BannerAdapter extends PagerAdapter {
	private View rowView;
	private Context context;
	private Map<Integer, View> rowViews = new HashMap<Integer, View>();

	private int[] images = new int[]{R.drawable.t0,R.drawable.t1,R.drawable.t2,R.drawable.t3};
	
	public BannerAdapter(Context context) {
		this.context = context;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView((View) arg2);
	}

	@Override
	public void finishUpdate(View arg0) {

	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		BannerHolder holder = null;
		rowView = rowViews.get(arg0);
		if (rowView == null) {
			rowView = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
			//初始化组件
			holder = new BannerHolder(rowView);
			rowView.setTag(holder);
		} else {
			holder = (BannerHolder) rowView.getTag();
		}
		holder.getImageView().setBackgroundResource(images[arg1]);
		rowViews.put(arg1, rowView);
		((ViewPager) arg0).addView(rowView, 0);
		return rowView;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}

	private class BannerHolder {
		private View view;
		private ImageView imageview;

		private BannerHolder(View v) {
			view = v;
		}

		ImageView getImageView() {
			if (imageview == null) {
				imageview = (ImageView) view.findViewById(R.id.banner);
			}
			return imageview;
		}
	}

}
