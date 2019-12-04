package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Category;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

	private int categoryId;
	private String categoryType;

	public CategoryDTO(Category category) {
		this.categoryId = category.getCategoryId();
		this.categoryType = category.getCategoryType();
	}
}
