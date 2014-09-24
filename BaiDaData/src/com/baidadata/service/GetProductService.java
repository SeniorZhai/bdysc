package com.baidadata.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.baidadata.domain.Product;

public class GetProductService {
	public List<Product> getProduct()
	{
		List<Product> products = new ArrayList<Product>();
		Document document = null;
		try {
			document = Jsoup.connect("http://www.bdysc.com/list-430160/p1.html").timeout(3000).get();
			if (document!=null) {
				Elements elements=document.getElementsByClass("mainlist");
				
                Log.i("html", elements.size()+"");
				for(Element element:elements)
				{
					Product product = new Product();
					product.setUrl(element.child(0).select("a").attr("href"));
					product.setProductImage(null);
					product.setImageUrl(element.select("img").first().absUrl("src"));
					product.setProductName(element.select("img").first().attr("alt"));
					product.setProductPrice(element.child(0).child(0).text());
					Log.i("url",element.child(0).select("a").attr("href"));
					products.add(product);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

}
