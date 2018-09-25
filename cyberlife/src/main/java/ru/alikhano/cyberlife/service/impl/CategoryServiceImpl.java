package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
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

	@Transactional
	public List<CategoryDTO> getAll() {
		List<Category> categories= categoryDao.getAll();
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categories.stream().forEach(category -> {
			CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
			categoriesDTO.add(categoryDTO);
		});
		
		return categoriesDTO;
	}

	@Transactional
	public CategoryDTO getById(int id) {
		return categoryMapper.categoryToCategoryDTO(categoryDao.getById(id));
	}

	
	@Transactional
	public CategoryDTO getByType(String catType) {
		return categoryMapper.categoryToCategoryDTO(categoryDao.getByType(catType));
	}

	
	@Transactional
	public void create(CategoryDTO categoryDTO){
	
		categoryDao.create(categoryMapper.categoryDTOtoCategory(categoryDTO));
		
	}

	@Override
	public int createAndGetId(CategoryDTO categoryDTO) {

		return categoryDao.createAndGetId(categoryMapper.categoryDTOtoCategory(categoryDTO));
	}

}
