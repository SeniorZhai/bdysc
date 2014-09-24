package com.baida.activity;

import com.baida.domain.Product;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.ListUtil;
import com.baida.util.WebViewUtil;
import com.baida.widget.LoadingDialog;
import com.baidadata.activity.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsActivity extends SwipeBackActivity {
	private ImageView product_detail_listview_image;
	private TextView product_detail_listview_text_name;
	private TextView product_detail_listview_text_price;
	private TextView product_detail_listview_text_describe;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;
	private Product product;
	private WebView productWebView;

	private WebViewUtil webViewUtil;
	private DataAsyncTask asyncTask;
	
	private LoadingDialog dialog;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_detail);
		int index = getIntent().getIntExtra("index", 0);
		product = ListUtil.products.get(index);
		dialog = new LoadingDialog(this);
		dialog.show();
		initView();
		
		asyncTask = new DataAsyncTask();
		asyncTask.execute("");
		setEdgeFromLeft();
	}

	@SuppressLint("SetJavaScriptEnabled") private void initView() {
		// TODO Auto-generated method stub
		product_detail_listview_image = (ImageView) findViewById(R.id.product_detail_listview_image);
		product_detail_listview_text_name = (TextView) findViewById(R.id.product_detail_listview_text_name);
		product_detail_listview_text_price = (TextView) findViewById(R.id.product_detail_listview_text_price);
		product_detail_listview_text_describe = (TextView) findViewById(R.id.product_detail_listview_text_describe);
		productWebView = (WebView) findViewById(R.id.product_webview);
		WebSettings webSettings = productWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setBuiltInZoomControls(true);
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		
		webViewUtil = new WebViewUtil();

	
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 10))
				.cacheInMemory().cacheOnDisc().build();

		product_detail_listview_text_name.setText(product.getProductName());
		product_detail_listview_text_price.setText(product.getProductPrice());
		product_detail_listview_text_describe.setText(product
				.getProductDescribe());
		imageLoader.displayImage(product.getImageUrl(),
				product_detail_listview_image, options);
	}
	
	private class DataAsyncTask extends AsyncTask<String, Integer, String>
	{

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			webViewUtil.GetItemWebView(product.getUrl());
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			productWebView.loadDataWithBaseURL(null,
					webViewUtil.getItemData(), "text/html", "utf-8", null);
			dialog.dismiss();
			productWebView.setVisibility(View.VISIBLE);
		}
		
	}

}
