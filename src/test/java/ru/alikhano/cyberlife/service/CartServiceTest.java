package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.service.impl.CartServiceImpl;
import ru.alikhano.cyberlife.supplier.CartSupplier;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
	@Mock
	private CartDao cartDao;
	@Mock
	private CartMapper cartMapper;
	@Mock
	private CartItemService cartItemService;

	@InjectMocks
	private CartServiceImpl cartService;

	private Cart cart;
	private CartDTO cartDTO;
	private List<CartDTO> cartsDTO;

	@Before
	public void init() {
		cart = CartSupplier.getCartWithSetCartItems();
		cartDTO = CartSupplier.getCartDTOWithSetCartItems();

		List<Cart> carts = new ArrayList<>();
		carts.add(cart);
		cartsDTO = new ArrayList<>();
		cartsDTO.add(cartDTO);

		Mockito.when(cartMapper.forward(cart)).thenReturn(cartDTO);
		Mockito.when(cartMapper.backward(cartDTO)).thenReturn(cart);
		Mockito.when(cartDao.getAll()).thenReturn(carts);
		Mockito.when(cartDao.getById(1)).thenReturn(cart);
		Mockito.when(cartDao.createAndGetId(cart)).thenReturn(1);
		Mockito.doNothing().when(cartItemService).delete(Mockito.any());
	}
	
	@Test
	public void create() {
		cartService.create(cartDTO);
		Mockito.verify(cartDao).create(cart);
	}
	
	@Test
	public void update() {
		cartService.update(cartDTO);
		Mockito.verify(cartDao).merge(Mockito.any());
	}
	
	@Test
	public void getById() {
		CartDTO cartDTO = cartService.getById(1);
		assertEquals(cartDTO, this.cartDTO);
	}
	
	@Test
	public void getByIdFail() {
		CartDTO cartDTO = cartService.getById(2);
		assertNull(cartDTO);
	}
	
	@Test
	public void getAll() {
		List<CartDTO> list = cartService.getAll();
		assertEquals(list, cartsDTO);
		Mockito.verify(cartDao).getAll();
	}
	
	@Test
	public void createAndGetId() {
		int id = cartService.createAndGetId(cartDTO);
		assertEquals(1, id);
	}

	@Test
	public void deleteItemFromCart() {
		cartService.deleteItemFromCart(cartDTO, 1);
		assertEquals(0, cartDTO.getItems().size());
	}

}
