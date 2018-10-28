package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.DTO.SearchRequest;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.mapper.ProductInfoMapper;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.OrderItem;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.MessagingService;
import ru.alikhano.cyberlife.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	ProductDao productDaoMock;

	@Mock
	ProductMapper productMapper;

	@Mock
	ProductInfoMapper productInfoMapper;
	
	@Mock
	CategoryService categoryService;
	
	@Mock
	ConsciousnessService consService;

	@Mock
	OrderService orderServiceMock;

	@Mock
	OrderMapper orderMapperMock;

	@Mock
	MessagingService messagingService;

	@InjectMocks
	ProductServiceImpl productService;

	Product productMock;
	ProductDTO productDTOMock;
	List<Product> productsMock;
	List<ProductDTO> productsDTOMock;
	Orders orderMock;
	OrderDTO orderDTOMock;
	List<OrderDTO> orders;
	OrderItem orderItemMock;
	OrderItemDTO orderItemDTOMock;
	Set<OrderItemDTO> items;
	Category category;
	Consciousness cons;

	@Before
	public void init() {
		category = new Category(1, "education");
		cons = new Consciousness(1, "middle AI", "nothing special");
		ProductInfo productInfo = new ProductInfo();
		productMock = new Product(1, "rk800", "test description", 5, 1500.0, category, cons);
		productDTOMock = Mockito.mock(ProductDTO.class);
		productsMock = new ArrayList<>();
		productsDTOMock = new ArrayList<>();
		productsMock.add(productMock);
		productsDTOMock.add(productDTOMock);
		orderDTOMock = Mockito.mock(OrderDTO.class);
		orderItemDTOMock = Mockito.mock(OrderItemDTO.class);
		orders = new ArrayList<>();
		orders.add(orderDTOMock);
		items = new HashSet<>();
		items.add(orderItemDTOMock);
		orderItemMock = new OrderItem(1, 2, 1500.0, productMock, orderMock);
		Mockito.when(productDaoMock.getById(1)).thenReturn(productMock);
		Mockito.when(productDaoMock.getByModel("rk800")).thenReturn(productMock);
		Mockito.when(productDaoMock.getAll()).thenReturn(productsMock);
		Mockito.when(productMapper.productToProductDTO(productMock)).thenReturn(productDTOMock);
		Mockito.when(productMapper.productDTOtOProduct(productDTOMock)).thenReturn(productMock);
		Mockito.when(productInfoMapper.productToProductInfo(productMock)).thenReturn(productInfo);
		Mockito.doNothing().when(productDaoMock).delete(productMock);
		Mockito.doNothing().when(productDaoMock).update(productMock);
		Mockito.doNothing().when(productDaoMock).merge(productMock);
		Mockito.when(productDaoMock.searchParam("rk800", 1, 1, 0.0, 1500.0)).thenReturn(productsMock);
		Mockito.when(orderServiceMock.getAll()).thenReturn(orders);
		Mockito.doReturn(items).when(orderDTOMock).getOrderedItems();
		Mockito.doReturn(productDTOMock).when(orderItemDTOMock).getProduct();
		Mockito.doReturn("paid").when(orderDTOMock).getPaymentStatus();
		Mockito.doReturn(1).when(productDTOMock).getProductId();
		Mockito.doReturn("rk800").when(productDTOMock).getModel();

		Mockito.when(productDaoMock.getTopProducts()).thenReturn(productsMock);

	}

	@Test
	public void create() throws CustomLogicException {
		ProductDTO product = new ProductDTO();
		productService.create(product);
	}

	@Test(expected = CustomLogicException.class)
	public void createFail() throws CustomLogicException {
		productService.create(productDTOMock);
		productDaoMock.create(productMock);

	}

	@Test
	public void delete() throws CustomLogicException, IOException, TimeoutException {
		productService.delete(productDTOMock);
		Mockito.verify(productDaoMock).delete(productMock);

	}
	
	@Test
	public void deleteFail() throws CustomLogicException, IOException, TimeoutException {
		Mockito.doReturn("unpaid").when(orderDTOMock).getPaymentStatus();
		Mockito.doReturn("awaits pickup").when(orderDTOMock).getOrderStatus();
		String result = productService.delete(productDTOMock);
		assertEquals("failed", result);
		
		}
	@Test(expected=CustomLogicException.class)
	public void deleteFailNull() throws CustomLogicException, IOException, TimeoutException {
		productService.delete(null);
	}

	@Test
	public void update() throws IOException, TimeoutException, CustomLogicException {
		productService.update(productDTOMock);
		Mockito.verify(productDaoMock).update(productMock);

	}
	
	@Test(expected=CustomLogicException.class)
	public void updateFailNull() throws IOException, TimeoutException, CustomLogicException {
		productService.update(null);
	}

	@Test
	public void updateFailNegativeUnits() throws IOException, TimeoutException, CustomLogicException {
		Mockito.doReturn(-1).when(productDTOMock).getUnitsInStock();
		String result = productService.update(productDTOMock);
		assertEquals("negative units", result);

	}

	@Test
	public void updateFailNegativePrice() throws IOException, TimeoutException, CustomLogicException {
		Mockito.doReturn(-1.0).when(productDTOMock).getPrice();
		String result = productService.update(productDTOMock);
		assertEquals("negative price", result);
	}

	@Test
	public void updateDTO() throws IOException, TimeoutException, CustomLogicException {
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
	public void getById() throws CustomLogicException {
		ProductDTO productDTO = productService.getById(1);
		assertEquals(productDTO, productDTOMock);
	}

	@Test(expected=CustomLogicException.class)
	public void getByIdFail() throws CustomLogicException {
		ProductDTO productDTO = productService.getById(2);
		org.junit.Assert.assertNull(productDTO);
	}

	@Test
	public void getByModel() throws CustomLogicException {
		ProductDTO productDTO = productService.getByModel("rk800");
		assertEquals(productDTO, productDTOMock);
	}

	@Test
	public void getByModelFail() throws CustomLogicException {
		ProductDTO productDTO = productService.getByModel("rk900");
		org.junit.Assert.assertNull(productDTO);
	}

	@Test
	public void getDTO() {
		Product product = productService.getProductById(1);
		ProductDTO productDTO = new ProductDTO(product);
		assertEquals(product, productMock);
		assertTrue(productDTO.getClass().equals(ProductDTO.class));

	}

	@Test
	public void searchFail() {
		SearchRequest search = Mockito.mock(SearchRequest.class);
		Mockito.doReturn("rk900").when(search).getModel();
		Mockito.doReturn("child care").when(search).getCategory();
		Mockito.doReturn("low AI").when(search).getConsLevel();
		Mockito.doReturn(2000.0).when(search).getToPrice();
		Mockito.doReturn(0.0).when(search).getFromPrice();
		CategoryDTO category = Mockito.mock(CategoryDTO.class);
		ConsDTO cons = Mockito.mock(ConsDTO.class);
		Mockito.doReturn(1).when(category).getCatId();
		Mockito.doReturn(1).when(cons).getConsId();
		Mockito.doReturn(category).when(categoryService).getByType("child care");
		Mockito.doReturn(cons).when(consService).getByLevel("low AI");
		List<ProductInfo> list = productService.searchParam(search);
		assertTrue(list.size() == 0);

	}

	@Test
	public void search() {
		SearchRequest search =Mockito.mock(SearchRequest.class);
		Mockito.doReturn("rk800").when(search).getModel();
		Mockito.doReturn("education").when(search).getCategory();
		Mockito.doReturn("middle AI").when(search).getConsLevel();
		Mockito.doReturn(1500.0).when(search).getToPrice();
		CategoryDTO category = Mockito.mock(CategoryDTO.class);
		ConsDTO cons = Mockito.mock(ConsDTO.class);
		Mockito.doReturn(1).when(category).getCatId();
		Mockito.doReturn(1).when(cons).getConsId();
		Mockito.doReturn(category).when(categoryService).getByType("education");
		Mockito.doReturn(cons).when(consService).getByLevel("middle AI");
		List<ProductInfo> list = productService.searchParam(search);
		Mockito.verify(productDaoMock).searchParam("rk800", 1, 1, 0, 1500.0);
		assertEquals(list.size(), productsMock.size());
	}

	@Test
	public void merge() throws IOException, TimeoutException {
		productService.merge(productDTOMock);
		Mockito.verify(productDaoMock).merge(productMock);
	}

	@Test
	public void canBeDeleted() {
		boolean result = productService.canBeDeleted(productDTOMock);
		assertEquals(true, result);
	}
	
	@Test
	public void canBeDeletedFalse() {
		Mockito.doReturn("unpaid").when(orderDTOMock).getPaymentStatus();
		Mockito.doReturn("awaits pickup").when(orderDTOMock).getOrderStatus();
		boolean result = productService.canBeDeleted(productDTOMock);
		assertEquals(false, result);
	}

	@Test
	public void getTopProducts() {
		productService.getTopProducts();
		Mockito.verify(productDaoMock).getTopProducts();
	}

	@Test
	public void isInTop() {
		boolean result = productService.isInTop(productDTOMock);
		assertTrue(result);
	}

	@Test
	public void isInTopFail() {
		Product pr = new Product(2, "rk900", "test description", 5, 1500.0, category, cons);
		ProductDTO product = new ProductDTO(pr);
		boolean result = productService.isInTop(product);
		assertFalse(result);
	}

}
