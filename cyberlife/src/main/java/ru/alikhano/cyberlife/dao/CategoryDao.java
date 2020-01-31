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

	Category getByType(String catType);

	Integer createAndGetId(Category category);
	
}
