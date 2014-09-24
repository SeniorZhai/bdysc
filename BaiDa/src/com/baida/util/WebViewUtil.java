package com.baida.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.util.Log;

public class WebViewUtil {
	private Document doc;
	private Elements divAllItemElement;

	public String getItemData() {
		return divAllItemElement.toString();
	}

	public void GetItemWebView(String itemUrl, int flag) {
		try {
			switch (flag) {
			case 0:
				doc = Jsoup.connect(itemUrl).timeout(3000).get();
				Log.i("WEBURL", "-------------"+itemUrl);
				divAllItemElement = doc.getElementsByClass("box2");
				break;
			case 1:
				doc = Jsoup.connect(itemUrl).timeout(3000).get();
				divAllItemElement = doc.getElementsByClass("list");
				
				break;
			case 2:
				doc = Jsoup.connect(itemUrl).timeout(3000).get();
				divAllItemElement = doc.getElementsByClass("r_cont");
			default:
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
