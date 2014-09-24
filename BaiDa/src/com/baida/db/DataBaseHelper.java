package com.baida.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "baida";
	private static DataBaseHelper mDataBaseHelper;
	private static boolean isUsing = false;
	private static int times = 0;

	public synchronized static DataBaseHelper getInstance(Context context) {
		if (mDataBaseHelper == null) {
			mDataBaseHelper = new DataBaseHelper(context);
		}
		while (isUsing) {
			try {
				Thread.sleep(1000);
				times++;
				if (times > 5) {
					isUsing = false;
					times = 0;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isUsing = true;
		return mDataBaseHelper;
	}

	public static void closeDB() {
		isUsing = false;
	}

	private DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 地址
		
		db.execSQL("create table if not exists AddressList (name varchar(50),address  varchar(50),street varchar(50),chec varchar(50)"
				+ ",phone varchar(50))");
		// 收藏
		db.execSQL("create table if not exists CollectList (productName varchar(50),url varchar(50),productDescribe  varchar(50),productPrice varchar(50)"
				+ ",imageUrl varchar(50))");
		// 购物车
		db.execSQL("create table if not exists ShoppingList (productName varchar(50),url varchar(50),productDescribe  varchar(50),productPrice varchar(50)"
				+ ",imageUrl varchar(50))");
		//商品菜单
		db.execSQL("create table if not exists ProductList (productName varchar(50),url varchar(50),productDescribe  varchar(50),productPrice varchar(50)"
				+ ",imageUrl varchar(50))");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
