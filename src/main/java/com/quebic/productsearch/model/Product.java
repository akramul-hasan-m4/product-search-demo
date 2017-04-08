package com.quebic.productsearch.model;

import java.util.ArrayList;
import java.util.List;
import com.lovi.searchbox.annotation.Id;
import com.lovi.searchbox.annotation.Index;

public class Product {

	@Id
	@Index
	private int id;
	
	@Index
	private String title;
	
	@Index
	private double price;
	
	private String currencyType;
	
	@Index
    private List<String> productCategories = new ArrayList<>();
	
	public Product() {
	}
	
	public Product(int id, String title, double price, String currencyType, List<String> productCategories) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.currencyType = currencyType;
		this.productCategories = productCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public List<String> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<String> productCategories) {
		this.productCategories = productCategories;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", price=" + price + ", currencyType=" + currencyType
				+ ", productCategories=" + productCategories + "]";
	}
	
}
