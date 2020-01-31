package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Integer productId;
	private String model;
	private String description;
	private Integer unitsInStock;
	private Double price;
	private byte[] image;
	private CategoryDTO category;
	private ConsciousnessDTO cons;

}
