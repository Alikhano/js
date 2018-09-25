package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;

public interface CategoryService {
	
	List<CategoryDTO> getAll();

	CategoryDTO getById(int id);

	CategoryDTO getByType(String catType);

	void create(CategoryDTO categoryDTO);
	
	int createAndGetId(CategoryDTO categoryDTO);

}
