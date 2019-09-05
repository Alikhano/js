package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Category;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

	private int catId;
	private String catType;

	public CategoryDTO(Category category) {
		this.catId = category.getCategoryId();
		this.catType = category.getCategoryType();
	}
}
