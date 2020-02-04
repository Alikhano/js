package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {
	
	private String model;
	private String category;
	private String consLevel;
	private double fromPrice;
	private double toPrice;

	@Override
	public String toString() {
		return "SearchRequest [model=" + model + ", category=" + category + ", consLevel=" + consLevel + ", fromPrice="
				+ fromPrice + ", toPrice=" + toPrice + "]";
	}
	
	
	

}
