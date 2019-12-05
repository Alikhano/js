package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.mapper.CategoryMapper;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.service.impl.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	@Mock
	private CategoryDao categoryDao;
	@Mock
	private CategoryMapper categoryMapper;
	
	@InjectMocks
	private CategoryServiceImpl categoryService;

	private Category category;
	private CategoryDTO categoryDTO;
	
	@Before
	public void init() {
		category = new Category(1, "education");
		categoryDTO = new CategoryDTO(category);
		List<Category> categories = new ArrayList<>();
		categories.add(category);

		Mockito.when(categoryDao.getByType("education")).thenReturn(category);
		Mockito.when(categoryDao.getById(1)).thenReturn(category);
		Mockito.when(categoryDao.getAll()).thenReturn(categories);
		Mockito.when(categoryMapper.categoryDTOtoCategory(categoryDTO)).thenReturn(category);
		Mockito.when(categoryMapper.categoryToCategoryDTO(category)).thenReturn(categoryDTO);
		Mockito.when(categoryDao.createAndGetId(category)).thenReturn(1);
		Mockito.doNothing().when(categoryDao).create(category);

	}
	
	@Test
	public void create() {
		categoryService.create(categoryDTO);
		Mockito.verify(categoryDao).create(category);
	}

	
	@Test
	public void createAndGetId() {
		int id = categoryService.createAndGetId(categoryDTO);
		assertEquals(1, id);
	}
	
	@Test
	public void getByType() {
		CategoryDTO category = categoryService.getByType("education");
		assertEquals("education", category.getCategoryType());
	}
	
	@Test
	public void getById() {
		CategoryDTO category = categoryService.getById(1);
		assertEquals("education", category.getCategoryType());
	}
	
	@Test
	public void getAll() {
		categoryService.getAll();
		Mockito.verify(categoryDao).getAll();
	}
}
