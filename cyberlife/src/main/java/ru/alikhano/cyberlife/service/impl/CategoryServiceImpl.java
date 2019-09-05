package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.mapper.CategoryMapper;
import ru.alikhano.cyberlife.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public List<CategoryDTO> getAll() {
		List<CategoryDTO> categoriesDTO = new ArrayList<>();
		categoryDao.getAll().stream().forEach(category -> {
			CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
			categoriesDTO.add(categoryDTO);
		});
		
		return categoriesDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public CategoryDTO getById(int id) {

		return categoryMapper.categoryToCategoryDTO(categoryDao.getById(id));
	}


	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public CategoryDTO getByType(String catType) {

		return categoryMapper.categoryToCategoryDTO(categoryDao.getByType(catType));
	}


	/**
	 * {@inheritDoc}
	 */
	@Transactional
	public void create(CategoryDTO categoryDTO){

		categoryDao.create(categoryMapper.categoryDTOtoCategory(categoryDTO));
	}

	@Override
	@Transactional
	public int createAndGetId(CategoryDTO categoryDTO) {

		return categoryDao.createAndGetId(categoryMapper.categoryDTOtoCategory(categoryDTO));
	}

}
