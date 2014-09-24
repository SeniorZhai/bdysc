package com.baida.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baida.activity.R;
import com.baida.domain.Address;

public class AddressAdapter extends BaseAdapter {
	List<Address> products;
	private Context context;

	private TextView address_item_name;

	public AddressAdapter(Context context, List<Address> list) {
		super();
		this.context = context;
		this.products = list;
		
	}

	public void setAddress(List<Address> addresses) {
		this.products = addresses;
	}

	public List<Address> getProducts() {
		return products;
	}

	public void setProducts(List<Address> products) {
		this.products.addAll(products);
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
					R.layout.address_item, null);
		}

		address_item_name = (TextView) relativeLayout
				.findViewById(R.id.address_item_name);
		address_item_name.setText(products.get(position).getName());

		((TextView) relativeLayout.findViewById(R.id.address_item_address))
				.setText(products.get(position).getAddress());
		((TextView) relativeLayout.findViewById(R.id.address_item_phone))
				.setText(products.get(position).getJiedao());

		return relativeLayout;
	}
}
