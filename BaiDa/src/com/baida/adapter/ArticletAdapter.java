package com.baida.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baida.activity.R;
import com.baida.domain.Article;

public class ArticletAdapter extends BaseAdapter {
	List<Article> articles;
	private Context context;

	

	public ArticletAdapter(Context context, List<Article> articles) {
		super();
		this.context = context;
		this.articles = articles;			
	}

	public List<Article> getProducts() {
		return articles;
	}

	public void setProducts(List<Article> articles) {
		this.articles.addAll(articles);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return articles.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		RelativeLayout relativeLayout;
		if (convertView != null) {
			relativeLayout = (RelativeLayout) convertView;
		} else {
			relativeLayout = (RelativeLayout) View.inflate(context,
					R.layout.article_item, null);
		}

		((TextView) relativeLayout
				.findViewById(R.id.article_name))
				.setText(articles.get(position).getName());
		((TextView) relativeLayout
				.findViewById(R.id.article_time))
				.setText(articles.get(position).getTime());
		
		return relativeLayout;
	}

}
