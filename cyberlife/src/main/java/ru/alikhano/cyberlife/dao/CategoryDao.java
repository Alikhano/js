package ru.alikhano.cyberlife.dao;


import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Category;

@Repository
public interface CategoryDao extends GenericDao<Category> {

	Category getByType(String catType);
	
}
