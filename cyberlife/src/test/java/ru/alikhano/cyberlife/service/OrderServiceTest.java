package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.enums.OrderStatus;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;
import ru.alikhano.cyberlife.service.impl.OrderServiceImpl;
import ru.alikhano.cyberlife.supplier.CartSupplier;
import ru.alikhano.cyberlife.supplier.CustomerSupplier;
import ru.alikhano.cyberlife.supplier.OrderSupplier;
import ru.alikhano.cyberlife.supplier.ProductSupplier;
import ru.alikhano.cyberlife.supplier.UserSupplier;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	@Mock
	private OrderDao orderDao;
	@Mock
	private OrderMapper orderMapper;
	@Mock
	private CartMapper cartMapper;
	@Mock
	private UserService userService;
	@Mock
	private CustomerService customerService;
	@Mock
	private ProductService productService;
	@Mock
	private OrderItemService orderItemService;
	@Mock
	private CartItemService cartItemService;
	@Mock
	private CartService cartService;

	@InjectMocks
	private OrderServiceImpl orderService;

	private Orders order;
	private List<Orders> orders;
	private Map<Integer, Double> revenueMonth;
	private OrderDTO orderDTO;

	@Before
	public void init() {
		order = OrderSupplier.getEmptyOrder();
		orders = new ArrayList<>();
		orders.add(order);

		orderDTO = OrderSupplier.getEmptyOrderDTO();

		revenueMonth = new HashMap<>();
		revenueMonth.put(10, 1500.0);

		CustomerDTO emptyCustomerDTO = new CustomerDTO(2, null, null,
													   null, null, null, null);

		Mockito.doNothing().when(orderDao).update(order);
		Mockito.when(orderDao.getById(1)).thenReturn(order);
		Mockito.when(orderDao.createAndGetId(order)).thenReturn(1);
		Mockito.when(orderDao.getMonthlyRevenue()).thenReturn(revenueMonth);
		Mockito.when(orderDao.getWeeklyRevenue()).thenReturn(1500.0);
		Mockito.when(orderDao.getOrdersByCustomerId(1)).thenReturn(orders);
		Mockito.when(orderDao.getAll()).thenReturn(orders);
		Mockito.when(orderMapper.backward(orderDTO)).thenReturn(order);
		Mockito.when(orderMapper.forward(order)).thenReturn(orderDTO);
		Mockito.doReturn(UserSupplier.getUserDTO(1, "user")).when(userService).getByUsernameDTO("user");
		Mockito.doReturn(UserSupplier.getUserDTO(2, "empty")).when(userService).getByUsernameDTO("empty");
		Mockito.doReturn(CustomerSupplier.getCustomerDTO()).when(customerService).getByUserId(1);
		Mockito.doReturn(emptyCustomerDTO).when(customerService).getByUserId(2);
		Mockito.doReturn(ProductSupplier.getProductDTO()).when(productService).selectForUpdate(1);
		Mockito.doReturn(Collections.singletonList(ProductSupplier.getProductDTO())).when(productService).getTopProducts();

	}
	
	@Test
	public void create() {
		Mockito.doNothing().when(orderDao).create(order);
		orderService.create(orderDTO);
		Mockito.verify(orderDao).create(order);
	}
	
	@Test
	public void getAll() {
		List<OrderDTO> list = orderService.getAll();
		Mockito.verify(orderDao).getAll();
		assertEquals(list.size(), orders.size());
	}
	
	@Test
	public void getById() {
		OrderDTO order = orderService.getById(1);
		Mockito.verify(orderDao).getById(1);
		assertNotNull(order);
	}
	
	@Test
	public void getByIdFail() {
		OrderDTO order = orderService.getById(2);
		assertNull(order);
	}
	
	@Test
	public void update() throws IOException, TimeoutException {
		orderService.update(orderDTO);
		Mockito.verify(orderDao).update(order);
	}
	
	@Test
	public void getByCustomerId() {
		orderService.getByCustomerId(1);
		Mockito.verify(orderDao).getOrdersByCustomerId(1);
	}
	
	@Test
	public void getByCustomerIdFail() {
		List<OrderDTO> list = orderService.getByCustomerId(2);
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void createAndGetId() {
		int id = orderService.createAndGetId(orderDTO);
		Mockito.verify(orderDao).createAndGetId(order);
		assertEquals(1, id);
	}
	
	@Test
	public void getMonthlyRevenue() {
		Map<Integer, Double> revenue = orderService.getMonthlyRevenue();
		Mockito.verify(orderDao).getMonthlyRevenue();
		assertEquals(revenueMonth.size(), revenue.size());
	}
	
	
	@Test
	public void getWeeklyRevenue() {
		orderService.getWeeklyRevenue();
		Mockito.verify(orderDao).getWeeklyRevenue();
	}
	
	@Test
	public void cartToOrder() throws CustomLogicException, IOException, TimeoutException {
		String result = orderService.cartToOrder(orderDTO, CartSupplier.getCartDTOWithSetCartItems(), "user");
		assertEquals("success", result);
	}
	
	@Test
	public void isInTop() {
		boolean result = orderService.isInTop(OrderSupplier.getOrderDTO());
		assertTrue(result);
	}

	@Test(expected = CustomLogicException.class)
	public void cartToOrderThrowsExceptionForEmptyCustomerProfile() throws TimeoutException, CustomLogicException,
			IOException {
		orderService.cartToOrder(orderDTO, CartSupplier.getCartDTOWithSetCartItems(), "empty");
	}

	@Test
	public void changeOrderStatus() {
		orderService.changeOrderStatus(1, OrderStatus.RECEIVED, PaymentStatus.PAID);
		assertEquals(PaymentStatus.PAID, order.getPaymentStatus());
		assertEquals(OrderStatus.RECEIVED, order.getOrderStatus());
	}
}
