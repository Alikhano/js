package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.model.OrderItem;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.service.impl.MessagingService;
import ru.alikhano.cyberlife.service.impl.OrderServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

	@Mock
	private OrderDao orderDaoMock;

	@Mock
	private OrderMapper orderMapperMock;
	
	@Mock
	private UserService userServiceMock;
	
	@Mock
	private CustomerService customerServiceMock;
	
	@Mock
	private ProductService productService;

	@InjectMocks
	private OrderServiceImpl orderServiceMock;

	private Orders orderMock;
	private OrderDTO orderDTOMock;
	private List<Orders> orders;
	private Customer customerMock;
	private OrderItem orderItemMock;
	private OrderItemDTO orderItemDTOMock;
	private Set<OrderItemDTO> itemsDTOMock;
	private Map<Integer, Double> revenueMonth;
	private UserDTO userDTOMock;
	private CustomerDTO customerDTOMock;
	private AddressDTO addressMock;
	private ProductDTO productDTOMock;
	private List<ProductDTO> products;
	private CartDTO cartDTOMock;
	private CartItemDTO cartItemDTOMock;
	private Set<CartItemDTO> cartItems;

	@Before
	public void init() {
		AddressDTO addressDTO = Mockito.mock(AddressDTO.class);
		customerMock = Mockito.mock(Customer.class);
		orderItemMock = Mockito.mock(OrderItem.class);
		userDTOMock = Mockito.mock(UserDTO.class);
		customerDTOMock = Mockito.mock(CustomerDTO.class);
		addressMock = Mockito.mock(AddressDTO.class);
		productDTOMock = Mockito.mock(ProductDTO.class);
		products = new ArrayList<>();
		products.add(productDTOMock);
		cartDTOMock = Mockito.mock(CartDTO.class);
		cartItemDTOMock = Mockito.mock(CartItemDTO.class);
		orderDTOMock = Mockito.mock(OrderDTO.class);
		orderItemDTOMock = Mockito.mock(OrderItemDTO.class);
		cartItems = new HashSet<>();
		cartItems.add(cartItemDTOMock);
		orderMock = Mockito.mock(Orders.class);
		orders = new ArrayList<>();
		orders.add(orderMock);
		itemsDTOMock = new HashSet<>();
		itemsDTOMock.add(orderItemDTOMock);
		
		revenueMonth = new HashMap<>();
		revenueMonth.put(10, 1500.0);
		Mockito.doNothing().when(orderDaoMock).create(orderMock);
		Mockito.doNothing().when(orderDaoMock).update(orderMock);
		Mockito.when(orderDaoMock.getById(1)).thenReturn(orderMock);
		Mockito.when(orderDaoMock.createAndGetId(orderMock)).thenReturn(1);
		Mockito.when(orderDaoMock.getMonthlyRevenue()).thenReturn(revenueMonth);
		Mockito.when(orderDaoMock.getWeeklyRevenue()).thenReturn(1500.0);
		Mockito.when(orderDaoMock.getOrdersByCustomerId(1)).thenReturn(orders);
		Mockito.when(orderDaoMock.getAll()).thenReturn(orders);
		Mockito.when(orderMapperMock.orderDTOtoOder(orderDTOMock)).thenReturn(orderMock);
		Mockito.when(orderMapperMock.orderToOrderDTO(orderMock)).thenReturn(orderDTOMock);
		Mockito.doReturn(userDTOMock).when(userServiceMock).getByUsernameDTO("user");
		Mockito.doReturn(1).when(userDTOMock).getUserId();
		Mockito.doReturn(customerDTOMock).when(customerServiceMock).getByUserId(1);
		Mockito.doReturn(addressDTO).when(customerDTOMock).getAddress();
		Mockito.doReturn("email").when(customerDTOMock).getEmail();
		Mockito.doReturn("name").when(customerDTOMock).getLastName();
		Mockito.doReturn("credit card").when(orderDTOMock).getPaymentType();
		Mockito.doReturn(itemsDTOMock).when(orderDTOMock).getOrderedItems();
		Mockito.doReturn(1).when(productDTOMock).getUnitsInStock();
		Mockito.doReturn(1).when(cartItemDTOMock).getQuantity();
		Mockito.doReturn(1500.0).when(cartDTOMock).getGrandTotal();
		Mockito.doReturn(cartItems).when(cartDTOMock).getItems();
		Mockito.doReturn(products).when(productService).getTopProducts();
		Mockito.doReturn(productDTOMock).when(orderItemDTOMock).getProduct();
		Mockito.doReturn(1).when(productDTOMock).getProductId();
		Mockito.doReturn("paid").when(orderDTOMock).getPaymentStatus();
		Mockito.doReturn(productDTOMock).when(productService).selectForUpdate(1);
		Mockito.doReturn(productDTOMock).when(cartItemDTOMock).getProduct();
	}
	
	@Test
	public void create() {
		orderServiceMock.create(orderDTOMock);
		Mockito.verify(orderDaoMock).create(orderMock);
	}
	
	@Test
	public void getAll() {
		List<OrderDTO> list = orderServiceMock.getAll();
		Mockito.verify(orderDaoMock).getAll();
		assertEquals(list.size(), orders.size());
	}
	
	@Test
	public void getById() {
		OrderDTO order = orderServiceMock.getById(1);
		Mockito.verify(orderDaoMock).getById(1);
		assertTrue(order != null);
	}
	
	@Test
	public void getByIdFail() {
		OrderDTO order = orderServiceMock.getById(2);
		assertNull(order);
	}
	
	@Test
	public void update() throws IOException, TimeoutException {
		orderServiceMock.update(orderDTOMock);
		Mockito.verify(orderDaoMock).update(orderMock);
	}
	
	@Test
	public void getByCustomerId() {
		orderServiceMock.getByCustomerId(1);
		Mockito.verify(orderDaoMock).getOrdersByCustomerId(1);
	}
	
	@Test
	public void getByCustomerIdFail() {
		List<OrderDTO> list = orderServiceMock.getByCustomerId(2);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void createAndGetId() {
		int id = orderServiceMock.createAndGetId(orderDTOMock);
		Mockito.verify(orderDaoMock).createAndGetId(orderMock);
		assertEquals(1, id);
	}
	
	@Test
	public void getMonthlyRevenue() {
		Map<Integer, Double> revenue = orderServiceMock.getMonthlyRevenue();
		Mockito.verify(orderDaoMock).getMonthlyRevenue();
		assertEquals(revenueMonth.size(), revenue.size());
	}
	
	
	@Test
	public void getWeeklyRevenue() {
		orderServiceMock.getWeeklyRevenue();
		Mockito.verify(orderDaoMock).getWeeklyRevenue();
	}
	
	@Test
	public void cartToOrder() throws CustomLogicException, IOException, TimeoutException {
		String result = orderServiceMock.cartToOrder(orderDTOMock, cartDTOMock, "user");
		assertEquals("success", result);
	}
	
	@Test
	public void isInTop() {
		boolean result = orderServiceMock.isInTop(orderDTOMock);
		assertTrue(result);
	}
}
