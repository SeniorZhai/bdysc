package com.baida.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebViewUtil {
	private Document doc;
	private Elements divAllItemElement;
	public String getItemData() {
			return divAllItemElement.toString();
	}
	public void GetItemWebView(String itemUrl){
		try {
			doc=Jsoup.connect(itemUrl).timeout(3000).get();
			divAllItemElement=doc.getElementsByClass("box2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
