package ru.alikhano.cyberlife.config.rabbitmq;

import java.io.Serializable;

public class ProductJson extends ProductToJsonParser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//
	private int productId;
	private String model;
	private String category;
	private String consLevel;
	private double price;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getConsLevel() {
		return consLevel;
	}
	public void setConsLevel(String consLevel) {
		this.consLevel = consLevel;
	}

	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ProductJson [productId=" + productId + ", model=" + model + ", category=" + category + ", consLevel="
				+ consLevel + ", price=" + price + "]";
	}


}
