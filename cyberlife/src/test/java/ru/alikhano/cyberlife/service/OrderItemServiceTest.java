package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dao.OrderItemDao;
import ru.alikhano.cyberlife.mapper.OrderItemMapper;
import ru.alikhano.cyberlife.model.OrderItem;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.OrderItemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemServiceTest {
	
	@Mock
	private OrderItemDao orderItemDaoMock;
	
	@Mock
	private OrderItemMapper orderItemMapperMock;
	
	@InjectMocks
	private OrderItemServiceImpl orderItemServiceMock;

	private OrderItem orderItemMock;
	private OrderItemDTO orderItemDTOMock;
	private List<OrderItem> items;
	private Set<OrderItemDTO> itemsDTO;
	private Orders orderMock;
	private OrderDTO orderDTOMock;
	private Product productMock;
	
	@Before
	public void init() {
		orderMock = Mockito.mock(Orders.class);
		productMock = Mockito.mock(Product.class);
		orderItemMock = new OrderItem(1, 2, 1500.0, productMock, orderMock);
		orderItemDTOMock = new OrderItemDTO(orderItemMock);
		orderDTOMock = Mockito.mock(OrderDTO.class);
		items = new ArrayList<>();
		itemsDTO = new HashSet<>();
		items.add(orderItemMock);
		itemsDTO.add(orderItemDTOMock);
		Mockito.doNothing().when(orderItemDaoMock).create(orderItemMock);
		Mockito.doNothing().when(orderItemDaoMock).update(orderItemMock);
		Mockito.doNothing().when(orderItemDaoMock).delete(orderItemMock);
		Mockito.when(orderItemDaoMock.getById(1)).thenReturn(orderItemMock);
		Mockito.when(orderItemMapperMock.orderItemDTOtoOrderItem(orderItemDTOMock)).thenReturn(orderItemMock);
		Mockito.when(orderItemMapperMock.orderItemToOrderItemDTO(orderItemMock)).thenReturn(orderItemDTOMock);
		Mockito.doReturn(itemsDTO).when(orderDTOMock).getOrderedItems();
	}
	
	@Test
	public void create() {
		orderItemServiceMock.create(orderItemDTOMock);
		Mockito.verify(orderItemDaoMock).create(orderItemMock);
	}
	
	@Test
	public void delete() {
		orderItemServiceMock.delete(orderItemDTOMock);
		Mockito.verify(orderItemDaoMock).delete(orderItemMock);
	}
	
	@Test
	public void update() {
		orderItemServiceMock.update(orderItemDTOMock);
		Mockito.verify(orderItemDaoMock).update(orderItemMock);
	}
	
	@Test
	public void getById() {
		OrderItemDTO item = orderItemServiceMock.getById(1);
		assertEquals(1, item.getOrderItemId());
		Mockito.verify(orderItemDaoMock).getById(1);
	}
	
	@Test
	public void getByIdFail() {
		OrderItemDTO item = orderItemServiceMock.getById(2);
		assertNull(item);
	}
	
	@Test
	public void getAll() {
		orderItemServiceMock.deleteAll(orderDTOMock);
		Mockito.verify(orderItemDaoMock).delete(orderItemMock);
	}

}
