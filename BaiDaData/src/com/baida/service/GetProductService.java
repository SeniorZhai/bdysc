package com.baida.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baida.domain.Product;

public class GetProductService {
	public List<Product> getProduct(int page) {
		List<Product> products = new ArrayList<Product>();
		Document document = null;

		try {
			document = Jsoup
					.connect("http://www.bdysc.com/list-430160/p"+page+".html").timeout(3000).get();
			if (document != null) {
				Elements elements = document.getElementById("prodcutListUl").children();
				for (Element element : elements) {
					Product product = new Product();
					product.setUrl("http://www.bdysc.com"+element.child(0).select("a")
							.attr("href"));
					product.setImageUrl(element.select("img").first()
							.absUrl("src"));
					product.setProductName(element.select("img").first()
							.attr("alt"));
					product.setProductDescribe("易商城自营店");
					product.setProductPrice(element.child(2).text().replace(element.child(2).child(0).text(), "").trim());
					products.add(product);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

}
