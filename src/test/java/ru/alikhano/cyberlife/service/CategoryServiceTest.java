package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

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
import ru.alikhano.cyberlife.supplier.CategorySupplier;

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

	private static final String TEST_CATEGORY_TYPE = "education";
	
	@Before
	public void init() {
		category = CategorySupplier.getCategory();
		categoryDTO = CategorySupplier.getCategoryDTO();

		Mockito.when(categoryDao.getByType(TEST_CATEGORY_TYPE)).thenReturn(category);
		Mockito.when(categoryDao.getById(1)).thenReturn(category);
		Mockito.when(categoryDao.getAll()).thenReturn(CategorySupplier.getCategories());
		Mockito.when(categoryMapper.backward(categoryDTO)).thenReturn(category);
		Mockito.when(categoryMapper.forward(category)).thenReturn(categoryDTO);
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
		CategoryDTO category = categoryService.getByType(TEST_CATEGORY_TYPE);
		assertEquals(TEST_CATEGORY_TYPE, category.getCategoryType());
	}
	
	@Test
	public void getById() {
		CategoryDTO category = categoryService.getById(1);
		assertEquals(TEST_CATEGORY_TYPE, category.getCategoryType());
	}
	
	@Test
	public void getAll() {
		categoryService.getAll();
		Mockito.verify(categoryDao).getAll();
	}
}
