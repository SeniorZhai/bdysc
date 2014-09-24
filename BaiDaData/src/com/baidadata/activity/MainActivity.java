package com.baidadata.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.baidadata.domain.Product;
import com.baidadata.service.GetProductService;

public class MainActivity extends Activity {
private List<Product> products = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new MyAsyncTask().execute("");
    }


   public class MyAsyncTask extends AsyncTask<String, String, List<Product>>{

	@Override
	protected List<Product> doInBackground(String... params) {
		// TODO Auto-generated method stub
		List<Product> tempProducts = new GetProductService().getProduct();
		return tempProducts;
	}
	@Override
	protected void onPostExecute(List<Product> product) {
		// TODO Auto-generated method stub
		products.addAll(product);
		Log.i("tag", products.size()+"");
//		super.onPostExecute(product);
	}
	   
   }
    
}
