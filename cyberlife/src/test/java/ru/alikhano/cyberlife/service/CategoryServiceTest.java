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

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.mapper.CategoryMapper;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.service.impl.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {
	
	@Mock
	CategoryDao categoryDao;
	
	@Mock
	CategoryMapper categoryMapper;
	
	@InjectMocks
	CategoryServiceImpl categoryService;
	
	Category categoryMock;
	CategoryDTO categoryDTOMock;
	List<Category> categories;
	List<CategoryDTO> categoriesDTO;
	
	@Before
	public void init() {
		categoryMock = new Category(1, "education");
		categoryDTOMock = new CategoryDTO(categoryMock);
		categories = new ArrayList<>();
		categoriesDTO = new ArrayList<>();
		categories.add(categoryMock);
		categoriesDTO.add(categoryDTOMock);
		Mockito.when(categoryDao.getByType("education")).thenReturn(categoryMock);
		Mockito.when(categoryDao.getById(1)).thenReturn(categoryMock);
		Mockito.when(categoryDao.getAll()).thenReturn(categories);
		Mockito.when(categoryMapper.categoryDTOtoCategory(categoryDTOMock)).thenReturn(categoryMock);
		Mockito.when(categoryMapper.categoryToCategoryDTO(categoryMock)).thenReturn(categoryDTOMock);
		Mockito.when(categoryDao.createAndGetId(categoryMock)).thenReturn(1);
		
		Mockito.doAnswer((i) -> {
			System.out.println("Category is created");
			return null;
		}).when(categoryDao).create(categoryMock);
		
		
	}
	
	@Test
	public void create() {
		categoryService.create(categoryDTOMock);
	}
	
	@Test
	public void createAndGetId() {
		int id = categoryService.createAndGetId(categoryDTOMock);
		assertEquals(1, id);
	}
	
	@Test
	public void getByType() {
		CategoryDTO category = categoryService.getByType("education");
		assertEquals("education", category.getCatType());
	}
	
	@Test
	public void getById() {
		CategoryDTO category = categoryService.getById(1);
		assertEquals("education", category.getCatType());
	}
	
	@Test
	public void getAll() {
		List<CategoryDTO> list = categoryService.getAll();
		assertEquals(categoriesDTO, list);
	}

}