package ru.alikhano.cyberlife.DTO;

public class SearchRequest {
	
	private String model;
	private String category;
	private String consLevel;
	private double fromPrice;
	private double toPrice;
	
	
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
	public double getFromPrice() {
		return fromPrice;
	}
	public void setFromPrice(double fromPrice) {
		this.fromPrice = fromPrice;
	}
	public double getToPrice() {
		return toPrice;
	}
	public void setToPrice(double toPrice) {
		this.toPrice = toPrice;
	}
	
	@Override
	public String toString() {
		return "SearchRequest [model=" + model + ", category=" + category + ", consLevel=" + consLevel + ", fromPrice="
				+ fromPrice + ", toPrice=" + toPrice + "]";
	}
	
	
	

}
