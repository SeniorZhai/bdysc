package com.baida.db;

import java.util.ArrayList;
import java.util.List;

import com.baida.domain.Address;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AddressManage {
	/**
	 * 向数据库中加入一条记录
	 * 
	 * @param address
	 * @param context
	 */
	public void addaddress(Address address, Context context) {
		SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
				.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", address.getName());
		contentValues.put("address", address.getAddress());
		contentValues.put("phone", address.getPhone());
		contentValues.put("street", address.getJiedao());
		contentValues.put("chec", address.getCheck());

		if (sqLiteDatabase != null) {
			sqLiteDatabase.insert("AddressList", null, contentValues);
			sqLiteDatabase.close();
			DataBaseHelper.closeDB();
		}
	}

	/**
	 * 取出address集合数据
	 * 
	 * @param context
	 * @return
	 */
	public List<Address> getaddresss(Context context, String type) {
		List<Address> addresss = new ArrayList<Address>();
		SQLiteDatabase sqLiteDatabase = DataBaseHelper.getInstance(context)
				.getReadableDatabase();
		if (sqLiteDatabase != null) {
			Cursor cursor = sqLiteDatabase.query("AddressList", null, null,
					null, null, null, null);
			while (cursor.moveToNext()) {
				Address address = new Address();

				address.setName(cursor.getString(cursor.getColumnIndex("name")));
				address.setAddress(cursor.getString(cursor
						.getColumnIndex("address")));
				address.setPhone(cursor.getString(cursor
						.getColumnIndex("phone")));
				address.setJiedao(cursor.getString(cursor
						.getColumnIndex("street")));
				address.setCheck(cursor.getString(cursor.getColumnIndex("chec")));


				addresss.add(address);
			}
			sqLiteDatabase.close();
			DataBaseHelper.closeDB();
		}
		return addresss;
	}

}
