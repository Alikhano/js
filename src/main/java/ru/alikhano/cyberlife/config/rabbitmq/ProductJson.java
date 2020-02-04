package ru.alikhano.cyberlife.config.rabbitmq;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductJson extends ProductToJsonParser implements Serializable{

	private static final long serialVersionUID = 1L;

	private int productId;
	private String model;
	private String catType;
	private String consLevel;
	private double price;

	@Override
	public String toString() {
		return "ProductJson [productId=" + productId + ", model=" + model + ", category=" + catType + ", consLevel="
				+ consLevel + ", price=" + price + "]";
	}
}
