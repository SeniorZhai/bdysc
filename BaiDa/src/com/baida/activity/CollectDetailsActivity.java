package com.baida.activity;

import com.baida.db.ShoppingManage;
import com.baida.domain.Product;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.ListUtil;
import com.baida.util.WebViewUtil;
import com.baida.widget.LoadingDialog;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class CollectDetailsActivity extends SwipeBackActivity implements
		OnClickListener {
	private ImageView collect_detail_listview_image;
	private TextView collect_detail_listview_text_name;
	private TextView collect_detail_listview_text_price;
	private TextView collect_detail_listview_text_describe;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;

	private Product product;
	private WebView productWebView;

	private Button collect_buy_btn;
	private Button collect_shopping_btn;

	private WebViewUtil webViewUtil;
	private DataAsyncTask asyncTask;

	private LoadingDialog dialog;

	private ProgressBar progressBar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collect_detail);
		progressBar = (ProgressBar) findViewById(R.id.mian_listview_progress);
		int index = getIntent().getIntExtra("index", 0);

		product = ListUtil.collects.get(index);

		dialog = new LoadingDialog(this);
		dialog.show();
		initView();

		asyncTask = new DataAsyncTask();
		asyncTask.execute("");
		setEdgeFromLeft();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initView() {
		// TODO Auto-generated method stub
		collect_detail_listview_image = (ImageView) findViewById(R.id.collect_detail_listview_image);
		collect_detail_listview_text_name = (TextView) findViewById(R.id.collect_detail_listview_text_name);
		collect_detail_listview_text_price = (TextView) findViewById(R.id.collect_detail_listview_text_price);
		collect_detail_listview_text_describe = (TextView) findViewById(R.id.collect_detail_listview_text_describe);
		collect_buy_btn = (Button) findViewById(R.id.collect_buy_btn);
		collect_buy_btn.setOnClickListener(this);
		collect_shopping_btn = (Button) findViewById(R.id.collect_shopping_btn);
		collect_shopping_btn.setOnClickListener(this);
		
		productWebView = (WebView) findViewById(R.id.collect_webview);

		WebSettings webSettings = productWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);

		// webSettings.setBuiltInZoomControls(true);
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webViewUtil = new WebViewUtil();

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(this));
		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 10))
				.cacheInMemory().cacheOnDisc().build();

		collect_detail_listview_text_name.setText(product.getProductName());
		collect_detail_listview_text_price.setText(product.getProductPrice());
		collect_detail_listview_text_describe.setText(product
				.getProductDescribe());
		imageLoader.displayImage(product.getImageUrl(),
				collect_detail_listview_image, options);
	}

	private class DataAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			webViewUtil.GetItemWebView(product.getUrl(), 0);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				productWebView.loadDataWithBaseURL(null,
						webViewUtil.getItemData(), "text/html", "utf-8", null);
				productWebView.setVisibility(View.VISIBLE);
				dialog.dismiss();
			} catch (Exception e) {
				// TODO: handle exception
				dialog.dismiss();
				progressBar.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), "请检查你的网络链接",
						Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.collect_buy_btn:
			Toast.makeText(getApplicationContext(), "暂不支持此服务",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.collect_shopping_btn:
			new ShoppingManage().addProduct(product, getApplicationContext());
			break;

		default:
			break;
		}
	}
}
