package com.baida.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.baida.domain.Product;
import com.baida.util.ListUtil;

public class GetProductService {
	public List<Product> getProduct(int page, int menu, String search) {
		List<Product> products = new ArrayList<Product>();
		Document document = null;

		try {
			switch (menu) {
			case 0:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430160/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 1:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430168/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 2:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430152/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 3:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430174/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 4:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430181/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 5:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430197/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 6:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430269/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 7:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430303/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 8:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430328/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 9:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 10:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/b50107-p" + page
										+ ".html").timeout(3000).get();
				break;
			// 搜索
			case 11:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/product_list.jsp?keyword="
										+ search + "&cid=c_10000&page=" + page)
						.timeout(3000).get();
				break;

			// 食品饮料酒水

			case 12:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-600021/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 13:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-590007/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 14:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430085/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 15:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430096/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 16:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-590007/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 17:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430120/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 18:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430131/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 19:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430139/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 20:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430144/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 21:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430152/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 22:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430160/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 23:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430164/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 24:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430168/p" + page
										+ ".html").timeout(3000).get();
				break;
			// 厨房清洁、纸
			case 25:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430174/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 26:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430181/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 27:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430189/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 28:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430197/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 29:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430207/p" + page
										+ ".html").timeout(3000).get();
				break;

			// 日用家居、锅具餐具

			case 30:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430221/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 31:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430226/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 32:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430233/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 33:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430240/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 34:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430242/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 35:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430256/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 36:
				document = Jsoup
						.connect("http://www.bdysc.com/list-430265/p" + page

						+ ".html").timeout(3000).get();
				break;
			// 母婴玩具
			case 37:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430269/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 38:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430276/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 39:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430284/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 40:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430288/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 41:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430296/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 42:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430303/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 43:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430314/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 44:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430318/p" + page
										+ ".html").timeout(3000).get();
				break;
			// 美妆
			case 45:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430328/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 46:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430340/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 47:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430350/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 48:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430355/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 49:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430361/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 50:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430367/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 51:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430371/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 52:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430374/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 53:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430377/p" + page
										+ ".html").timeout(3000).get();
				break;

			case 54:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430381/p" + page
										+ ".html").timeout(3000).get();
				break;

			// 生活电器、帮工文娱
			case 55:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430386/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 56:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430395/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 57:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430401/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 58:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430405/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 59:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430423/p" + page
										+ ".html").timeout(3000).get();
				break;

			// 礼品专区
			case 60:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430434/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 61:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430437/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 62:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430440/p" + page
										+ ".html").timeout(3000).get();
				break;
			case 63:
				document = Jsoup
						.connect(
								"http://www.bdysc.com/list-430443/p" + page
										+ ".html").timeout(3000).get();
				break;

			default:
				break;

			}

			String elementkey = document.getElementsByClass(
					"result_key_notfound").text();
			ListUtil.elementkey = elementkey;
			
			if (document != null&&elementkey.equals("")) {

				Elements elements = document.getElementById("prodcutListUl")
						.children();

				for (Element element : elements) {
					Product product = new Product();
					product.setUrl("http://www.bdysc.com"
							+ element.child(0).select("a").attr("href"));
					product.setImageUrl(element.select("img").first()
							.absUrl("src"));
					product.setProductName(element.select("img").first()
							.attr("alt"));
					product.setProductDescribe("易商城自营店");
					product.setProductPrice(element.child(2).text()
							.replace(element.child(2).child(0).text(), "")
							.trim());

					products.add(product);

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return products;
	}
}
