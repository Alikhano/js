package ru.alikhano.cyberlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="model")
	private String model;
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="units")
	private int unitsInStock;
	
	@Column(name="price")
	private double price;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Product() {}

	public Product(int id, String model, String category, String description, int unitsInStock, double price) {
		super();
		this.id = id;
		this.model = model;
		this.category = category;
		this.description = description;
		this.unitsInStock = unitsInStock;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", model=" + model + ", category=" + category + ", description=" + description
				+ ", unitsInStock=" + unitsInStock + ", price=" + price + "]";
	}
	
	
	

}
