package ru.alikhano.cyberlife.dto;

import ru.alikhano.cyberlife.model.Category;

public class CategoryDTO {

	private int catId;
	private String catType;
	
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public String getCatType() {
		return catType;
	}
	public void setCatType(String catType) {
		this.catType = catType;
	}
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		this.catId = category.getCategoryId();
		this.catType = category.getCategoryType();
	}
}
