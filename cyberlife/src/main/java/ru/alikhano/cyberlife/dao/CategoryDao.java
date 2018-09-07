package ru.alikhano.cyberlife.dao;

import java.util.List;

import ru.alikhano.cyberlife.model.Category;

public interface CategoryDao {

	List<Category> getCategoryList();

	Category getCategoryById(int id);

	Category getCategoryByType(String catType);

	void addCategory(Category category);

}
