package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfo {
	
	private Integer productId;
	private String model;
	private String description;
	private Integer unitsInStock;
	private Double price;
	private String category;
	private String cons;
}
