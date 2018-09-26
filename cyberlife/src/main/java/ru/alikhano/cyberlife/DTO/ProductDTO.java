package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.Product;

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
	
	public ProductDTO() {
		
	}
	
	public ProductDTO (Product product) {
		this.productId = product.getProductId();
		this.model = product.getModel();
		this.description = product.getDescription();
		this.unitsInStock = product.getUnitsInStock();
		this.price = product.getPrice();
		
		this.category = new CategoryDTO();
		this.category.setCatId(product.getCategory().getCatId());
		this.category.setCatType(product.getCategory().getCatType());
		this.cons = new ConsDTO();
		this.cons.setConsId(product.getCons().getConsId());
		this.cons.setLevel(product.getCons().getLevel());
		this.cons.setDescription(product.getCons().getDescription());
	}

}
