package ru.alikhano.cyberlife.DTO;

public class ProductDTO {
	
	private int productId;
	private String model;
	private String description;
	private int unitsInStock;
	private double price;
	private byte[] image;
	private CategoryDTO category;
	private ConsDTO cons;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public CategoryDTO getCategory() {
		return category;
	}
	public void setCategory(CategoryDTO category) {
		this.category = category;
	}
	public ConsDTO getCons() {
		return cons;
	}
	public void setCons(ConsDTO cons) {
		this.cons = cons;
	}

}
