package ru.alikhano.cyberlife.dao;


import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Category;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface CategoryDao extends GenericDao<Category> {

	/** 
	 * enables database search by category of a product
	 * @param catType category type parameter to search in database
	 * @return instance of category
	 */
	Category getByType(String catType);
	
	/** 
	 * creates new category and returns it's id
	 * @param category instance of Category to add to database
	 * @return id of new category
	 */
	int createAndGetId(Category category);
	
}
