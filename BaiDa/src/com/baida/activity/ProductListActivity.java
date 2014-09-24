package com.baida.activity;

import java.util.ArrayList;
import java.util.List;

import com.baida.adapter.ProductAdapter;
import com.baida.domain.Product;
import com.baida.service.GetProductService;
import com.baida.swipeback.SwipeBackActivity;
import com.baida.util.HttpsUtil;
import com.baida.util.ListUtil;
import com.baida.widget.XListView;
import com.baida.widget.XListView.IXListViewListener;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProductListActivity extends SwipeBackActivity implements
		IXListViewListener {
	private XListView productListView;
	// private View footView;
	private List<Product> products = new ArrayList<Product>();
	private ProductAdapter adapter;
	private int page = 1;
	private int menu = 0;
	private String search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_list_activity);
		productListView = (XListView) findViewById(R.id.product_listview);

		adapter = new ProductAdapter(this, products);
		productListView.setPullLoadEnable(true);
		productListView.setXListViewListener(this);
		productListView.setAdapter(adapter);

		productListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProductListActivity.this,
						ProductDetailsActivity.class);
				intent.putExtra("index", arg2 - 1);
				intent.putExtra("flag", "productlist");
				startActivity(intent);

			}
		});
		menu = getIntent().getIntExtra("position", 0);
		search = getIntent().getStringExtra("search");
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "请检查你的网络链接",
					Toast.LENGTH_SHORT).show();
		}

	}

	public class MyAsyncTask extends AsyncTask<String, String, List<Product>> {

		@Override
		protected List<Product> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Product> tempProducts = new GetProductService().getProduct(
					page, menu, search);

			return tempProducts;
		}

		@Override
		protected void onPostExecute(List<Product> product) {
			ListUtil.products = products;
			adapter.setProducts(product);
			adapter.notifyDataSetChanged();
			onLoad();
			if (!ListUtil.elementkey.equals("")) {
				Toast.makeText(getApplicationContext(), "亲~~商品已经加载完了哦！！！",
						Toast.LENGTH_SHORT).show();
			} else if (ListUtil.products.size() == 0) {
				Toast.makeText(getApplicationContext(), "亲~~网络貌似出了点问题哦！！！",
						Toast.LENGTH_SHORT).show();

			}
		}

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "请检查你的网络链接",
					Toast.LENGTH_SHORT).show();
			onLoad();
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		
		if (HttpsUtil.isConnect(getApplicationContext())) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(getApplicationContext(), "请检查你的网络链接",
					Toast.LENGTH_SHORT).show();
			onLoad();
		}
		page++;
	}

	// 刷新结束
	private void onLoad() {
		productListView.stopRefresh();
		productListView.stopLoadMore();
		productListView.setRefreshTime("now");
	}

}
