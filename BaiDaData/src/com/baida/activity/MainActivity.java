package com.baida.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.baida.adapter.ProductAdapter;
import com.baida.domain.Product;
import com.baida.service.GetProductService;
import com.baida.util.ListUtil;
import com.baidadata.activity.R;

public class MainActivity extends Activity {
	private ListView productListView;
	private View footView;
	private List<Product> products = new ArrayList<Product>();
	private ProductAdapter adapter;
	private boolean isLoading = false;
	private int page = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		productListView = (ListView) findViewById(R.id.product_listview);
		footView = View.inflate(this, R.layout.foot, null);
		productListView.addFooterView(footView);
		adapter = new ProductAdapter(this, products);
		productListView.setAdapter(adapter);
		productListView.setOnScrollListener(new MyScrollListener());
		productListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						ProductDetailsActivity.class);
				intent.putExtra("index", arg2);
				startActivity(intent);

			}
		});

		new MyAsyncTask().execute("");
	}

	public class MyAsyncTask extends AsyncTask<String, String, List<Product>> {

		@Override
		protected List<Product> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Product> tempProducts = new GetProductService()
					.getProduct(page);

			return tempProducts;
		}

		@Override
		protected void onPostExecute(List<Product> product) {
			ListUtil.products = products;
			isLoading = false;
		
			adapter.setProducts(product);
			adapter.notifyDataSetChanged();
			if (product.size() == 0) {
				Toast.makeText(getApplicationContext(), "已经加载完毕",
						Toast.LENGTH_SHORT).show();
				productListView.removeFooterView(footView);
				isLoading = true;

			}
		}

	}

	public class MyScrollListener implements OnScrollListener {

		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {

		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (firstVisibleItem + visibleItemCount == totalItemCount
					&& isLoading == false) {
				isLoading = true;
				page++;
				new MyAsyncTask().execute("");
			}
		}

	}
}
