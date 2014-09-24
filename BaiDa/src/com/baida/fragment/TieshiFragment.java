package com.baida.fragment;

import java.util.ArrayList;
import java.util.List;

import com.baida.activity.ArticleDetailsActivity;
import com.baida.activity.R;
import com.baida.adapter.ArticletAdapter;
import com.baida.domain.Article;
import com.baida.service.GetArticleService;
import com.baida.util.HttpsUtil;
import com.baida.util.ListUtil;
import com.baida.widget.XListView;
import com.baida.widget.XListView.IXListViewListener;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class TieshiFragment extends Fragment implements IXListViewListener {
	private View mParent;
	private FragmentActivity mActivity;
	private XListView articleListView;
	// private View footView;
	private ArticletAdapter adapter;
	private List<Article> articles = new ArrayList<Article>();
	private int page = 1;

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

		articleListView = (XListView) mParent
				.findViewById(R.id.article_listview);

		articleListView.setPullLoadEnable(true);

		articleListView.setXListViewListener(this);

		adapter = new ArticletAdapter(mActivity, articles);

		articleListView.setAdapter(adapter);

		articleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(mActivity,
						ArticleDetailsActivity.class);
				intent.putExtra("index", arg2 - 1);
				startActivity(intent);
			}
		});

		if (HttpsUtil.isConnect(mActivity)) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(mActivity, "请检查你的网络链接", Toast.LENGTH_SHORT).show();
		}

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_buy, container, false);

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

	public class MyAsyncTask extends AsyncTask<String, Integer, List<Article>> {

		@Override
		protected List<Article> doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<Article> tempArticles = new GetArticleService()
					.getProduct(page);
			return tempArticles;
		}

		@Override
		protected void onPostExecute(List<Article> result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			ListUtil.articles = articles;
			onLoad();
			adapter.setProducts(result);
			adapter.notifyDataSetChanged();
			if (ListUtil.articlekey.equals("暂无公告 ！")) {
				Toast.makeText(mActivity, "亲~~我们正在努力更新中~~", Toast.LENGTH_SHORT)
						.show();
			} else if (result.size() == 0) {
				Toast.makeText(mActivity, "请检查你的网络链接", Toast.LENGTH_SHORT)
						.show();
			}
		}

	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub

		if (HttpsUtil.isConnect(mActivity)) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(mActivity, "请检查你的网络链接", Toast.LENGTH_SHORT).show();
			onLoad();
		}
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub

		if (HttpsUtil.isConnect(mActivity)) {
			new MyAsyncTask().execute("");
		} else {
			Toast.makeText(mActivity, "请检查你的网络链接", Toast.LENGTH_SHORT).show();
			onLoad();
		}
		page++;

	}

	// 刷新结束
	private void onLoad() {
		articleListView.stopRefresh();
		articleListView.stopLoadMore();
		articleListView.setRefreshTime("now");
	}

}
