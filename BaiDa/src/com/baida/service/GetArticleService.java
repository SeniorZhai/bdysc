package com.baida.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.baida.domain.Article;
import com.baida.util.ListUtil;



public class GetArticleService {
	public List<Article> getProduct(int page) {
		List<Article> products = new ArrayList<Article>();
		Document document = null;

		try {
			document = Jsoup
					.connect("http://www.bdysc.com/notice/list.jsp?cid=c_520007&page="+page).timeout(3000).get();
			String elementkey = document.getElementsByClass("list").first().text();
			ListUtil.articlekey = elementkey;
			if (document != null&&!elementkey.equals("ÔÝÎÞ¹«¸æ £¡")) {
				Element elements = document.getElementsByClass("list").first();
				Element element2 = elements.child(0); 

				for (Element element : element2.children()) {
					Article product = new Article();
					product.setUrl("http://www.bdysc.com"+element.select("a")
							.attr("href"));
					product.setName(element.select("a")
							.attr("title"));
					product.setTime(element.child(1).text());
//			    	Log.i("URL",element.child(1).text());
					products.add(product);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

}
