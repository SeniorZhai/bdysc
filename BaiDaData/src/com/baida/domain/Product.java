package com.baida.domain;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String url;
	private String productName;
	//private SoftReference<Bitmap> productImage;
	private String productPrice;
	private String imageUrl;
	private String productDescribe;

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

/*	public SoftReference<Bitmap> getProductImage() {
		return productImage;
	}

	public void setProductImage(SoftReference<Bitmap> productImage) {
		this.productImage = productImage;
	}*/

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

}
