package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.mapper.CategoryMapper;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	CategoryMapper categoryMapper;

	public List<CategoryDTO> getCategoryDTOList() {
		List<Category> categories= categoryDao.getCategoryList();
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categories.stream().forEach(category -> {
			CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
			categoriesDTO.add(categoryDTO);
		});
		
		return categoriesDTO;
	}

	public CategoryDTO getCategoryDTOById(int id) {
		return categoryMapper.categoryToCategoryDTO(categoryDao.getCategoryById(id));
	}

	
	public CategoryDTO getCategoryDTOByType(String catType) {
		return categoryMapper.categoryToCategoryDTO(categoryDao.getCategoryByType(catType));
	}

	
	public void addCategory(CategoryDTO categoryDTO) {
		categoryDao.addCategory(categoryMapper.categoryDTOtoCategory(categoryDTO));
		
	}

}
