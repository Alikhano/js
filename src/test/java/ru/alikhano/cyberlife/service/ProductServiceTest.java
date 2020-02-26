package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dto.ProductInfo;
import ru.alikhano.cyberlife.dto.SearchRequest;
import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.mapper.ProductInfoMapper;
import ru.alikhano.cyberlife.mapper.ProductMapper;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.MessagingService;
import ru.alikhano.cyberlife.service.impl.ProductServiceImpl;
import ru.alikhano.cyberlife.supplier.OrderSupplier;
import ru.alikhano.cyberlife.supplier.ProductSupplier;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	@Mock
	private ProductDao productDao;
	@Mock
	private ProductMapper productMapper;
	@Mock
	private ProductInfoMapper productInfoMapper;
	@Mock
	private CategoryService categoryService;
	@Mock
	private ConsciousnessService consService;
	@Mock
	private OrderService orderService;
	@Mock
	private MessagingService messagingService;

	@InjectMocks
	private ProductServiceImpl productService;

	private Product newProduct;
	private ProductDTO newProductDTO;
	private Product product;
	private ProductDTO productDTO;
	private OrderDTO orderDTO;
	private List<Product> products;

	@Before
	public void init() {
		ProductInfo productInfo = Mockito.mock(ProductInfo.class);

		newProduct = ProductSupplier.getProduct(2, "ab123");
		newProductDTO = ProductSupplier.getProductDTO(2, "ab123");

		product = ProductSupplier.getProduct(1, "rk800");
		productDTO = ProductSupplier.getProductDTO(1, "rk800");
		products = Arrays.asList(newProduct, product);

		orderDTO = OrderSupplier.getOrderDTO();

		Mockito.when(productDao.getById(1)).thenReturn(product);
		Mockito.when(productDao.getByModel("rk800")).thenReturn(product);

		Mockito.when(productDao.getAll()).thenReturn(products);
		Mockito.when(productMapper.forward(newProduct)).thenReturn(newProductDTO);
		Mockito.when(productMapper.backward(newProductDTO)).thenReturn(newProduct);
		Mockito.when(productMapper.forward(product)).thenReturn(productDTO);
		Mockito.when(productMapper.backward(productDTO)).thenReturn(product);
		Mockito.when(productInfoMapper.productToProductInfo(newProduct)).thenReturn(productInfo);

		Mockito.when(orderService.getAll()).thenReturn(Collections.singletonList(orderDTO));
		Mockito.when(productDao.getTopProducts()).thenReturn(products);

	}

	@Test
	public void create() throws CustomLogicException {
		Mockito.when(productDao.getByModel("ab123")).thenReturn(null);
		productService.create(newProductDTO);
		Mockito.verify(productDao).create(newProduct);
	}

	@Test(expected = CustomLogicException.class)
	public void createFail() throws CustomLogicException {
		productService.create(productDTO);
		productDao.create(product);

	}

	@Test
	public void delete() throws CustomLogicException, IOException, TimeoutException {
		productService.delete(productDTO);
		Mockito.verify(productDao).delete(product);

	}
	
	@Test
	public void deleteFail() throws CustomLogicException, IOException, TimeoutException {
		OrderDTO unpaidOrderDTO = orderDTO;
		unpaidOrderDTO.setPaymentStatus(PaymentStatusDTO.PAID);
		unpaidOrderDTO.setOrderStatus(OrderStatusDTO.RECEIVED);
		String result = productService.delete(productDTO);
		assertEquals("failed", result);
		
		}
	@Test(expected=CustomLogicException.class)
	public void deleteFailNull() throws CustomLogicException, IOException, TimeoutException {
		productService.delete(null);
	}

	@Test
	public void update() throws IOException, TimeoutException, CustomLogicException {
		Mockito.doNothing().when(productDao).update(newProduct);
		productService.update(newProductDTO);
		Mockito.verify(productDao).update(newProduct);

	}
	
	@Test(expected=CustomLogicException.class)
	public void updateFailNull() throws IOException, TimeoutException, CustomLogicException {
		productService.update(null);
	}

	@Test
	public void updateFailNegativeUnits() throws IOException, TimeoutException, CustomLogicException {
		ProductDTO productDTOWithNegativeUnits = new ProductDTO(2, "ab123", "test description", -5,
											   1500.0, null, null, null);
		String result = productService.update(productDTOWithNegativeUnits);
		assertEquals("negative units", result);

	}

	@Test
	public void updateFailNegativePrice() throws IOException, TimeoutException, CustomLogicException {
		ProductDTO productDTOWithNegativePrice = new ProductDTO(2, "ab123", "test description", 5,
																-1500.0, null, null, null);
		String result = productService.update(productDTOWithNegativePrice);
		assertEquals("negative price", result);
	}

	@Test
	public void updateDTO() throws IOException, TimeoutException, CustomLogicException {
		productService.update(newProductDTO);
		Mockito.verify(productDao).update(newProduct);
	}

	@Test
	public void getAllProducts() {
		productService.getAll();
		Mockito.verify(productDao).getAll();
	}

	@Test
	public void getAll() {
		List<ProductDTO> productsDTO = productService.getAll();
		assertEquals(2, productsDTO.size());
	}

	@Test
	public void getById() throws CustomLogicException {
		ProductDTO productDTO = productService.getById(1);
		assertEquals(productDTO.getProductId(), productDTO.getProductId());
	}

	@Test(expected=CustomLogicException.class)
	public void getByIdFail() throws CustomLogicException {
		ProductDTO productDTO = productService.getById(3);
		assertNull(productDTO);
	}

	@Test
	public void getByModel() {
		ProductDTO productDTO = productService.getByModel("rk800");
		assertEquals(productDTO.getModel(), productDTO.getModel());
	}

	@Test
	public void getByModelFail() {
		ProductDTO productDTO = productService.getByModel("rk900");
		assertNull(productDTO);
	}

	@Test
	public void searchFail() {
		SearchRequest search = new SearchRequest("rk900", "child care", "low AI", 0.0, 2000.0);
		CategoryDTO category = new CategoryDTO(1, "child care");
		ConsciousnessDTO consciousness = new ConsciousnessDTO(1, "low AI", "low AI");
		Mockito.doReturn(category).when(categoryService).getByType("child care");
		Mockito.doReturn(consciousness).when(consService).getByLevel("low AI");
		List<ProductInfo> list = productService.searchParam(search);
		assertEquals(0,list.size());

	}

	@Test
	public void search() {
		Mockito.when(productDao.searchParam("rk800", 1, 1, 0.0, 1500.0)).thenReturn(products);
		SearchRequest search = new SearchRequest("rk800", "education", "middle AI", 0.0, 1500.0);
		CategoryDTO category = new CategoryDTO(1, "education");
		ConsciousnessDTO consciousness = new ConsciousnessDTO(1, "middle AI", "middle AI");
		Mockito.doReturn(category).when(categoryService).getByType("education");
		Mockito.doReturn(consciousness).when(consService).getByLevel("middle AI");
		List<ProductInfo> list = productService.searchParam(search);
		Mockito.verify(productDao).searchParam("rk800", 1, 1, 0.0, 1500.0);
		assertEquals(list.size(), products.size());
	}

	@Test
	public void searchWithAnyParameters() {
		SearchRequest search = new SearchRequest("rk800", "any", "any", 0.0, 1500.0);
		productService.searchParam(search);
		Mockito.verify(productDao).searchParam("rk800", 0, 0, 0.0, 1500.0);
	}

	@Test
	public void merge() throws IOException, TimeoutException {
		Mockito.doNothing().when(productDao).merge(newProduct);
		productService.merge(newProductDTO);
		Mockito.verify(productDao).merge(newProduct);
	}

	@Test
	public void getTopProducts() {
		productService.getTopProducts();
		Mockito.verify(productDao).getTopProducts();
	}

	@Test
	public void isInTop() {
		boolean result = productService.isInTop(productDTO);
		assertTrue(result);
	}

	@Test
	public void isInTopFail() {
		ProductDTO productNotInTop = ProductSupplier.getProductDTO(3, "not_in_top");
		boolean result = productService.isInTop(productNotInTop);
		assertFalse(result);
	}

	@Test
	public void isExistingById() {
		assertTrue(productService.isProductExistingById(1));
	}

	@Test
	public void isNotExistingById() {
		assertFalse(productService.isProductExistingById(4));
	}

	@Test
	public void selectForUpdate() {
		Mockito.when(productDao.selectForUpdate(1)).thenReturn(product);
		ProductDTO productDTO = productService.selectForUpdate(1);
		assertEquals("rk800", productDTO.getModel());
	}

}
