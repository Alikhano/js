package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private int productId;
	private String model;
	private String description;
	private int unitsInStock;
	private double price;
	private byte[] image;
	private CategoryDTO category;
	private ConsciousnessDTO cons;
	
	public ProductDTO (Product product) {
		this.productId = product.getProductId();
		this.model = product.getModel();
		this.description = product.getDescription();
		this.unitsInStock = product.getUnitsInStock();
		this.price = product.getPrice();
		
		this.category = new CategoryDTO();
		this.category.setCategoryId(product.getCategory().getCategoryId());
		this.category.setCategoryType(product.getCategory().getCategoryType());
		this.cons = new ConsciousnessDTO();
		this.cons.setConsId(product.getCons().getConsId());
		this.cons.setLevel(product.getCons().getLevel());
		this.cons.setDescription(product.getCons().getDescription());
	}

}
