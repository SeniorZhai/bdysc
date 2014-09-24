package com.baida.db;

import java.util.ArrayList;
import java.util.List;

import com.baida.domain.Product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ShoppingManage {
	/**
	 * 向数据库中加入一条记录
	 * 
	 * @param product
	 * @param context
	 */
	public void addProduct(Product product, Context context) {
		SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
				.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("productName", product.getProductName());
		contentValues.put("productPrice", product.getProductPrice());
		contentValues.put("productDescribe", product.getProductDescribe());
		contentValues.put("imageUrl", product.getImageUrl());
		contentValues.put("url", product.getUrl());
		if (sqLiteDatabase != null) {
			sqLiteDatabase.insert("ShoppingList", null, contentValues);
			sqLiteDatabase.close();
			DataBaseHelper.closeDB();
		}
	}

	/**
	 * 取出Product集合数据
	 * 
	 * @param context
	 * @return
	 */
	public List<Product> getProducts(Context context, String type) {
		List<Product> products = new ArrayList<Product>();
		SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
				.getReadableDatabase();

		if (sqLiteDatabase != null) {
			Cursor cursor = sqLiteDatabase.query("ShoppingList", null, null,
					null, null, null, null);
			while (cursor.moveToNext()) {
				Product product = new Product();

				product.setProductName(cursor.getString(cursor
						.getColumnIndex("productName")));
				product.setProductPrice(cursor.getString(cursor
						.getColumnIndex("productPrice")));
				product.setProductDescribe(cursor.getString(cursor
						.getColumnIndex("productDescribe")));
				product.setImageUrl(cursor.getString(cursor
						.getColumnIndex("imageUrl")));
				product.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				
				products.add(product);
			}
			sqLiteDatabase.close();
			DataBaseHelper.closeDB();
		}
		return products;
	}

	
}
