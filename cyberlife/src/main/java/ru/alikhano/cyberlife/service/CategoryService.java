package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CategoryDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface CategoryService {
	
	/**
	 * @return list of all categories
	 */
	List<CategoryDTO> getAll();

	/**
	 * searches for a specific category by id
	 * @param id of a category that user's searching for
	 * @return instance of CategoryDTO
	 */
	CategoryDTO getById(int id);

	/**
	 * searches for a specific category by name
	 * @param catType name of a category that user's searching for
	 * @return instance of CategoryDTO
	 */
	CategoryDTO getByType(String catType);

	/**
	 * creates new category
	 * @param categoryDTO
	 */
	void create(CategoryDTO categoryDTO);
	
	/**
	 * creates new category and returns it's id
	 * @param categoryDTO
	 * @return id of a newly created category
	 */
	int createAndGetId(CategoryDTO categoryDTO);

}
