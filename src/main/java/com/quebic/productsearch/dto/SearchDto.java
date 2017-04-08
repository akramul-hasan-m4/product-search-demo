package com.quebic.productsearch.dto;

import java.util.ArrayList;
import java.util.List;

public class SearchDto {

	private String titlePrefix;
	private List<String> productCategories = new ArrayList<>();
	private double minPrice;
	private double maxPrice;

	public String getTitlePrefix() {
		return titlePrefix;
	}

	public void setTitlePrefix(String titlePrefix) {
		this.titlePrefix = titlePrefix;
	}

	public List<String> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<String> productCategories) {
		this.productCategories = productCategories;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

}
