/*
 * @Title:  LoginService.java
 * @Copyright:Ltd. Copyright sundroid,  All rights reserved
 * @Description:  TODO
 * @author:  sundroid
 * @data:  2014-3-24 下午10:40:35
 * @version:  V1.0
 */
package com.baida.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class LoginService {
	/**
	 * 登陆
	 * 
	 * @param userid
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * 
	 */
	private DefaultHttpClient httpClient;
	public static HashMap<String, String> COOKIES_BAIDA = new HashMap<String, String>();

	public boolean loginStudy(String userid, String password,
			String validateCode) {
		boolean success = true;
		String response = "对不起";
		String url = "http://www.bdysc.com/login/handle/login_handler.jsp";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Cookie",
				"JSESSIONID=" + COOKIES_BAIDA.get("cookie"));

		httpClient = new DefaultHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("ValidateCode", validateCode));
		params.add(new BasicNameValuePair("loginId", userid));
		params.add(new BasicNameValuePair("password", password));
		System.out.println(userid + "   " + password + "  " + validateCode);
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				CookieStore cookieStore = httpClient.getCookieStore();
				List<Cookie> cookies = cookieStore.getCookies();
				for (Cookie cookie : cookies) {
					COOKIES_BAIDA.put(cookie.getName(), cookie.getValue());
					System.out.print("cookie" + cookie.getValue());
				}
				System.out.println(strResult);
				response=new String(strResult.getBytes("ISO-8859-1"),"UTF-8");
				response = strResult;
			}
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println(response);
		if (response.contains("error")) {
			success = false;
		}
		return success;
	}

	public boolean first() {
		boolean success = true;
		String response = "对不起";
		String url = "http://www.bdysc.com/login/sign_in.jsp";
		HttpPost httpPost = new HttpPost(url);
		httpClient = new DefaultHttpClient();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				CookieStore cookieStore = httpClient.getCookieStore();
				List<Cookie> cookies = cookieStore.getCookies();
				for (Cookie cookie : cookies) {
					COOKIES_BAIDA.put("cookie", cookie.getValue());
					System.out.print("cookie" + cookie.getValue());
				}
//				System.out.println(strResult);
//				// response=new
				// String(strResult.getBytes("ISO-8859-1"),"UTF-8");

				response = strResult;
			}
		} catch (Exception e) {
//			System.out.println("error");
			e.printStackTrace();
		}
		System.out.println(response);
		if (response.contains("error")) {
			success = false;
		}
		return success;
	}

	public Bitmap getBitmap(String url) {
       
		HttpPost httpPost = new HttpPost(url);
		httpClient = new DefaultHttpClient();
		httpPost.setHeader("Cookie",
				"JSESSIONID=" + COOKIES_BAIDA.get("cookie"));

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 */
				byte[] image = EntityUtils
						.toByteArray(httpResponse.getEntity());
				 Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0,
							image.length);
//				System.out.println(image);
//			
//				System.out.print(image.toString());
				return bitmap;

			}
		} catch (Exception e) {
//			System.out.println("error");
			e.printStackTrace();
		}
return null;
	}
}
