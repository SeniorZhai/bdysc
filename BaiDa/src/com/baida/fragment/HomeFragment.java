package com.baida.fragment;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.baida.activity.ProductListActivity;
import com.baida.activity.R;
import com.baida.adapter.BannerAdapter;
import com.baida.adapter.GridViewAdapter;
import com.baida.util.CharsetUtil;
import com.baida.widget.MyGridView;
import com.baida.widget.ScrollViewExtend;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HomeFragment extends Fragment implements OnClickListener {
	private View mParent;
	private FragmentActivity mActivity;
	/**
	 * 视图
	 */
	private ViewPager viewPager;
	/**
	 * 导航组
	 */
	private ViewGroup group;
	/**
	 * 导航图标数组
	 */
	private ImageView[] images;

	private ImageView centerimagview;

	private ImageButton title_share;

	private Intent intent;

	private ScrollViewExtend scrollViewExtend;
//	private RelativeLayout linearLayout;
	private int index = 0;

	private EditText fragment_home_edt;
	private String search;
	private ImageButton title_search;

	private int[] im = new int[] { R.drawable.t0, R.drawable.t1, R.drawable.t2,
			R.drawable.t3 };
	private MyGridView hotgridview;

	private MyGridView catgridview;
	/**
	 * 当前显示的图片id
	 */
	private int currentItem = 0;

	private ScheduledExecutorService scheduledExecutorService;
	// 切换当前显示的图片
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	/**
	 * Create a new instance of DetailsFragment, initialized to show the text at
	 * 'index'.
	 */
	public static HomeFragment newInstance(int index) {
		HomeFragment f = new HomeFragment();

		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		mActivity = getActivity();
		mParent = getView();
		super.onActivityCreated(savedInstanceState);
		init();
		// 初始化活到列表导航
		initListNavigation();

		viewPager.setAdapter(new BannerAdapter(mActivity));

		viewPager.setOnPageChangeListener(new pagerListener());

	

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		// height = view.getMeasuredHeight();
		return view;
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub

		super.onHiddenChanged(hidden);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		super.onStop();
	}

	private void initListNavigation() {
		// TODO Auto-generated method stub
		int num = im.length;
		images = new ImageView[num];
		for (int i = 0; i < num; i++) {
			images[i] = new ImageView(mActivity);
			images[i].setLayoutParams(new LayoutParams(20, 20));
			images[i].setPadding(40, 0, 40, 0);
			if (i == 0) {
				images[i].setBackgroundResource(R.drawable.dotchecked);
			} else {
				images[i].setBackgroundResource(R.drawable.dotdefault);
			}
			group.addView(images[i]);
		}

	}

	private void init() {
		// TODO Auto-generated method stub
		// 导航组
		group = (ViewGroup) mParent.findViewById(R.id.viewGroup);
		// 活动组
		viewPager = (ViewPager) mParent.findViewById(R.id.viewpager);

		centerimagview = (ImageView) mParent.findViewById(R.id.centerimagview);

		title_share = (ImageButton) mParent.findViewById(R.id.title_share);

		title_share.setOnClickListener(this);

		/*linearLayout = (RelativeLayout) mParent
				.findViewById(R.id.guide_home_linear);*/

		fragment_home_edt = (EditText) mParent
				.findViewById(R.id.fragment_home_edt);
		title_search = (ImageButton) mParent.findViewById(R.id.title_search);
		title_search.setOnClickListener(this);
		scrollViewExtend = (ScrollViewExtend) mParent
				.findViewById(R.id.fragment_scroll);

		/*scrollViewExtend.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:

					break;
				case MotionEvent.ACTION_MOVE:
					index++;
					break;

				default:
					break;
				}
				if (event.getAction() == MotionEvent.ACTION_UP && index > 0) {
					index = 0;
					View containerView = ((ScrollViewExtend) v).getChildAt(0);
					if (containerView.getMeasuredHeight() <= v.getScrollY()
							+ v.getHeight()) {
						// TODO
						linearLayout.setVisibility(View.VISIBLE);

						// linearLayout.set

					} else {

						linearLayout.setVisibility(View.GONE);
					}
				}

				return false;
			}
		});*/

		centerimagview.setOnClickListener(this);

		hotgridview = (MyGridView) mParent.findViewById(R.id.hotgridview);

		int[] images = new int[] { R.drawable.t5, R.drawable.t6, R.drawable.t7,
				R.drawable.t8, R.drawable.t9, R.drawable.t10 };

		hotgridview.setAdapter(new GridViewAdapter(mActivity, images));

		hotgridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;
				case 4:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;
				case 5:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2);
					startActivity(intent);
					break;

				default:
					break;
				}

			}
		});
		catgridview = (MyGridView) mParent.findViewById(R.id.catgridview);
		int[] images2 = new int[] { R.drawable.t11, R.drawable.t12,
				R.drawable.t15, R.drawable.t14 };
		catgridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2 + 6);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2 + 6);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2 + 6);
					startActivity(intent);
					break;
				case 3:
					intent = new Intent();
					intent.setClass(mActivity, ProductListActivity.class);
					intent.putExtra("position", arg2 + 6);
					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
		catgridview.setAdapter(new GridViewAdapter(mActivity, images2));

	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		@Override
		public void run() {
			synchronized (viewPager) {
				currentItem = (currentItem + 1) % images.length;
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	private class pagerListener implements OnPageChangeListener {
		private int oldPosition = 0;

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {

			currentItem = position;
			images[position].setBackgroundResource(R.drawable.dotchecked);
			images[oldPosition].setBackgroundResource(R.drawable.dotdefault);
			oldPosition = position;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.title_search:

			search = fragment_home_edt.getText().toString();
			
		
			intent = new Intent();
			intent.setClass(mActivity, ProductListActivity.class);
			try {
				intent.putExtra("search", CharsetUtil.toISO_8859_1(search));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			intent.putExtra("position", 11);
			startActivity(intent);
			break;
		case R.id.centerimagview:
			intent = new Intent();
			intent.setClass(mActivity, ProductListActivity.class);
			intent.putExtra("position", 10);
			startActivity(intent);
			break;
		case R.id.title_share:
			intent = new Intent();
			String currentTrackMessage = "让小伙伴们知道百大易商城"+"http://www.bdysc.com/";
			intent.setAction(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, currentTrackMessage);
			startActivity(Intent.createChooser(intent, "告诉小伙伴"));
			break;

		default:
			break;
		}
	}

	public byte[] gbk2utf8(String chenese) {
		char c[] = chenese.toCharArray();
		byte[] fullByte = new byte[3 * c.length];
		for (int i = 0; i < c.length; i++) {
			int m = c[i];
			String word = Integer.toBinaryString(m);
			System.out.println(word);

			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			// 补零
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			sb.append(word);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");

			System.out.println(sb.toString());

			String s1 = sb.substring(0, 8);
			String s2 = sb.substring(8, 16);
			String s3 = sb.substring(16);

			byte b0 = Integer.valueOf(s1, 2).byteValue();
			byte b1 = Integer.valueOf(s2, 2).byteValue();
			byte b2 = Integer.valueOf(s3, 2).byteValue();
			byte[] bf = new byte[3];
			bf[0] = b0;
			fullByte[i * 3] = bf[0];
			bf[1] = b1;
			fullByte[i * 3 + 1] = bf[1];
			bf[2] = b2;
			fullByte[i * 3 + 2] = bf[2];

		}
		return fullByte;
	}

}
