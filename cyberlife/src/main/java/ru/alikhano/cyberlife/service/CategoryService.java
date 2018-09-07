package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CategoryDTO;

public interface CategoryService {
	
	List<CategoryDTO> getCategoryDTOList();

	CategoryDTO getCategoryDTOById(int id);

	CategoryDTO getCategoryDTOByType(String catType);

	void addCategory(CategoryDTO categoryDTO);

}
