package com.baida.service;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class DataService {
public void getData()
{
	Document document = null;

	try {
		document = Jsoup
				.connect("http://www.bdysc.com/").cookies(LoginService.COOKIES_BAIDA).timeout(3000).get();
		Log.i("Cookie", LoginService.COOKIES_BAIDA.get("cookie"));
		if (document != null) {
			Element elements = document.getElementsByClass("wbox").first();
            Log.i("WBOX", elements.toString());

		}
	} catch (IOException e) {
		e.printStackTrace();
	}


}
}
