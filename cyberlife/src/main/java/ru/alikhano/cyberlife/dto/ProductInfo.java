package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfo {
	
	private int productId;
	private String model;
	private String description;
	private int unitsInStock;
	private double price;
	private String category;
	private String cons;
}
