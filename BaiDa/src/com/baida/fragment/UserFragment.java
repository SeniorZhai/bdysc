package com.baida.fragment;

import com.baida.activity.FragmentAddressActivity;
import com.baida.activity.FragmentCollectActivity;
import com.baida.activity.FragmentHelpActivity;
import com.baida.activity.R;
import com.baida.config.ConstantS;
import com.baida.widget.RoundedImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserFragment extends Fragment implements OnClickListener {

	private View mParent;
	private Intent mIntent = null;
	private FragmentActivity mActivity;
	private ImageView userIcon = null;
	private TextView userName = null;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private SharedPreferences settings = null;
	private LinearLayout fragment_user_collect_linear,
			fragment_user_address_linear, fragment_userhelp_linear;

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
		fragment_user_collect_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_user_collect_linear);
		fragment_user_collect_linear.setOnClickListener(this);
		fragment_user_address_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_user_address_linear);
		fragment_user_address_linear.setOnClickListener(this);
		fragment_userhelp_linear = (LinearLayout) mParent
				.findViewById(R.id.fragment_userhelp_linear);
		fragment_userhelp_linear.setOnClickListener(this);

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_user, container, false);

		return view;
	}

	@SuppressWarnings("deprecation")
	private void initview() {
		// TODO Auto-generated method stub
		userIcon = (ImageView) mParent.findViewById(R.id.fragment_dsx_pic);
		userName = (TextView) mParent
				.findViewById(R.id.fragment_dsx_username_text);

		settings = mActivity.getSharedPreferences(ConstantS.PREFS_NAME, 0);
		String UserName = settings.getString("UserName", null);
		String ImageUrl = settings.getString("ImageUrl", null);
		String gender = settings.getString("gender", null);
		userName.setText(UserName);
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(mActivity));
		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 0))
				.cacheInMemory().cacheOnDisc().build();
		imageLoader.displayImage(ImageUrl, userIcon, options);
	}

	@Override
	public void onHiddenChanged(boolean hidden) {
		// TODO Auto-generated method stub
		initview();
		super.onHiddenChanged(hidden);
	}

	@Override
	public void onResume() {
		initview();
		super.onResume();
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
		case R.id.fragment_user_collect_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentCollectActivity.class);
			startActivity(mIntent);
			break;
		case R.id.fragment_userhelp_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentHelpActivity.class);

			startActivity(mIntent);

			break;
		case R.id.fragment_user_address_linear:
			mIntent = new Intent();
			mIntent.setClass(mActivity, FragmentAddressActivity.class);
			startActivity(mIntent);
			break;
		default:
			break;
		}

	}

}
