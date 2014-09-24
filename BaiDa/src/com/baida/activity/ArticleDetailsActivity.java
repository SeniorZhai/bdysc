package com.baida.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;

import com.baida.domain.Article;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.HttpsUtil;
import com.baida.util.ListUtil;
import com.baida.util.WebViewUtil;
import com.baida.widget.LoadingDialog;

public class ArticleDetailsActivity extends SwipeBackActivity {
	private LoadingDialog dialog;
	private WebViewUtil webViewUtil;
	private WebView articleWebView;
	private Article article;
	private ImageView network_error;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_detail);
		network_error = (ImageView) findViewById(R.id.network_error);
		dialog = new LoadingDialog(this);
		dialog.show();
		int index = getIntent().getIntExtra("index", 0);
		article = ListUtil.articles.get(index);
		articleWebView = (WebView) findViewById(R.id.article_webview);

		WebSettings webSettings = articleWebView.getSettings();
		// webSettings.setJavaScriptEnabled(true);
		// webSettings.setBuiltInZoomControls(true);
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		webViewUtil = new WebViewUtil();
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new DataAsyncTask().execute("");

		} else {
			/*Toast.makeText(getApplicationContext(), "网络好像有点问题~~",
					Toast.LENGTH_SHORT).show();*/
			network_error.setVisibility(View.VISIBLE);
			dialog.dismiss();
		}

		setEdgeFromLeft();
	}

	private class DataAsyncTask extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			webViewUtil.GetItemWebView(article.getUrl(), 1);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			articleWebView.loadDataWithBaseURL(null, webViewUtil.getItemData(),
					"text/html", "utf-8", null);
			dialog.dismiss();
			articleWebView.setVisibility(View.VISIBLE);
		}
	}
}
