package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	@Mock
	ProductDao productDaoMock;
	
	@Mock
	ProductMapper productMapper;
	
	@InjectMocks
	ProductServiceImpl productService;
	
	Product productMock;
	ProductDTO productDTOMock;
	List<Product> productsMock;
	List<ProductDTO> productsDTOMock;
	
	@Before
	public void init() {
		Category category = new Category(1,"education");
		Consciousness cons = new Consciousness(1, "middle AI", "nothing special");
		productMock = new Product(1,"rk800", "test description", 5, 1500.0, category, cons);
		productDTOMock = new ProductDTO(productMock);
		productsMock = new ArrayList<>();
		productsDTOMock = new ArrayList<>();
		productsMock.add(productMock);
		productsDTOMock.add(productDTOMock);
		Mockito.when(productDaoMock.getById(1)).thenReturn(productMock);
		Mockito.when(productDaoMock.getByModel("rk800")).thenReturn(productMock);
		Mockito.when(productDaoMock.getAll()).thenReturn(productsMock);
		Mockito.when(productMapper.productToProductDTO(productMock)).thenReturn(productDTOMock);	
		Mockito.when(productMapper.productDTOtOProduct(productDTOMock)).thenReturn(productMock);
		Mockito.doNothing().when(productDaoMock).delete(productMock);
		Mockito.doNothing().when(productDaoMock).update(productMock);
		Mockito.doNothing().when(productDaoMock).create(productMock);
		Mockito.doNothing().when(productDaoMock).merge(productMock);
		Mockito.when(productDaoMock.searchParam("rk800", 1, 1, 1500.0, 1500.0)).thenReturn(productsMock);
				
		}
	
	@Test
	public void create() throws CustomLogicException {
		ProductDTO product = new ProductDTO();
		productService.create(product);
		productDaoMock.create(productMock);
		
	}
	
	@Test
	public void delete() throws CustomLogicException, IOException, TimeoutException {
		productDaoMock.delete(productMock);
		Mockito.verify(productDaoMock).delete(productMock);
	

	}

	
	@Test 
	public void update() throws IOException, TimeoutException {
		productService.update(productDTOMock);
		Mockito.verify(productDaoMock).update(productMock);
	
	}
	
	@Test
	public void updateDTO() throws IOException, TimeoutException {
		productDaoMock.update(productMock);
		productService.update(productDTOMock);
	}
	
	@Test
	public void getAllProducts() {
		productService.getAll();
		Mockito.verify(productDaoMock).getAll();
	}
	
	@Test
	public void getAll() {
		List<ProductDTO> productsDTO = productService.getAll();
		assertEquals(productsDTO, productsDTOMock);
	}
	
	
	@Test
	public void getById() {
		ProductDTO productDTO = productService.getById(1);
		assertEquals(productDTO, productDTOMock);
	}
	
	@Test
	public void getByModel() throws CustomLogicException {
		ProductDTO productDTO = productService.getByModel("rk800");
		assertEquals(productDTO, productDTOMock);
	}
	
	
	@Test
	public void getDTO() {
		Product product = productService.getProductById(1);
		ProductDTO productDTO = new ProductDTO(product);
		assertEquals(product, productMock);
		assertTrue(productDTO.getClass().equals(ProductDTO.class));
	
	}
	
	@Test
	public void search() throws CustomLogicException {
		List<ProductInfo> list = productService.searchParam("rk800",1, 1, 1500.0, 1500.0);
		Mockito.verify(productDaoMock).searchParam("rk800",1, 1, 1500.0, 1500.0);
		//assertEquals(list.size(), productsMock.size());
		
	}
	
	@Test
	public void merge() throws IOException, TimeoutException {
		productService.merge(productDTOMock);
		Mockito.verify(productDaoMock).merge(productMock);
	}
	


}
