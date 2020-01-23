package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dao.OrderItemDao;
import ru.alikhano.cyberlife.mapper.OrderItemMapper;
import ru.alikhano.cyberlife.model.OrderItem;
import ru.alikhano.cyberlife.service.impl.OrderItemServiceImpl;
import ru.alikhano.cyberlife.supplier.OrderItemSupplier;
import ru.alikhano.cyberlife.supplier.OrderSupplier;


@RunWith(MockitoJUnitRunner.class)
public class OrderItemServiceTest {
	@Mock
	private OrderItemDao orderItemDao;
	@Mock
	private OrderItemMapper orderItemMapper;
	
	@InjectMocks
	private OrderItemServiceImpl orderItemService;

	private OrderItem    orderItem;
	private OrderItemDTO orderItemDTO;
	private OrderDTO     orderDTO;
	
	@Before
	public void init() {
		orderItem = OrderItemSupplier.getOrderItem();
		orderItemDTO = new OrderItemDTO(orderItem);
		orderDTO = OrderSupplier.getOrderDTO();

		Mockito.doNothing().when(orderItemDao).create(orderItem);
		Mockito.doNothing().when(orderItemDao).update(orderItem);
		Mockito.doNothing().when(orderItemDao).delete(orderItem);
		Mockito.when(orderItemDao.getById(1)).thenReturn(orderItem);
		Mockito.when(orderItemMapper.orderItemDTOtoOrderItem(orderItemDTO)).thenReturn(orderItem);
        Mockito.when(orderItemMapper.orderItemToOrderItemDTO(orderItem)).thenReturn(orderItemDTO);
	}
	
	@Test
	public void create() {
		orderItemService.create(orderItemDTO);
		Mockito.verify(orderItemDao).create(orderItem);
	}
	
	@Test
	public void delete() {
		orderItemService.delete(orderItemDTO);
		Mockito.verify(orderItemDao).delete(orderItem);
	}
	
	@Test
	public void update() {
		orderItemService.update(orderItemDTO);
		Mockito.verify(orderItemDao).update(orderItem);
	}
	
	@Test
	public void getById() {
		OrderItemDTO item = orderItemService.getById(1);
		assertEquals(1, item.getOrderItemId());
		Mockito.verify(orderItemDao).getById(1);
	}
	
	@Test
	public void getByIdFail() {
		OrderItemDTO item = orderItemService.getById(2);
		assertNull(item);
	}
	
	@Test
	public void deleteAll() {
		orderItemService.deleteAll(orderDTO);
		Mockito.verify(orderItemDao).delete(orderItem);
	}
}
