package com.baida.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baida.activity.R;
import com.baida.domain.Product;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class CollectAdapter extends BaseAdapter {
	List<Product> products;
	private Context context;
	private ImageLoader imageLoader = null;
	private DisplayImageOptions options = null;

	public CollectAdapter(Context context, List<Product> products) {
		super();
		this.context = context;
		this.products = products;
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));

		options = new DisplayImageOptions.Builder()
				.displayer(new RoundedBitmapDisplayer(0xff000000, 10))
				.cacheInMemory().cacheOnDisc().build();
	}

	public void setProducts(List<Product> products)
	{
		this.products = products;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return products.get(position);
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
					R.layout.collect_item, null);
		}
		ImageView imageView = (ImageView) relativeLayout
				.findViewById(R.id.collect_item_image);

		imageLoader.displayImage(products.get(position).getImageUrl(),
				imageView, options);
		((TextView) relativeLayout.findViewById(R.id.collect_item_name))
				.setText(products.get(position).getProductName());
		((TextView) relativeLayout.findViewById(R.id.collcet_item_describe))
				.setText(products.get(position).getProductDescribe());
		((TextView) relativeLayout.findViewById(R.id.collect_item_price))
				.setText(products.get(position).getProductPrice());
		return relativeLayout;
	}

}
