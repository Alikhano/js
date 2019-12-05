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

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.CartServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
	
	@Mock
	private CartDao cartDaoMock;
	@Mock
	private CartMapper cartMapper;

	@InjectMocks
	private CartServiceImpl cartServiceMock;

	private Cart cart;
	private CartDTO cartDTO;
	private List<CartDTO> cartsDTO;
	private Set<CartItem> items;

	@Before
	public void init() {
		Category category = new Category(1,"education");
		Consciousness cons = new Consciousness(1, "middle AI", "nothing special");
		Product product = new Product(1,"rk800", "test description", 5, 1500.0, category, cons);
		cart = new Cart(1, 1500.0, items);
		cartDTO = Mockito.mock(CartDTO.class);
		CartItem item = new CartItem(1, 1, 1500.0, product, cart);
		CartItemDTO itemDTO = new CartItemDTO(item);
		items = new HashSet<>();
		items.add(item);

		List<Cart> carts = new ArrayList<>();
		cartsDTO = new ArrayList<>();
		carts.add(cart);
		cartsDTO.add(cartDTO);
		Mockito.when(cartMapper.cartToCartDTO(cart)).thenReturn(cartDTO);
		Mockito.when(cartMapper.cartDTOtoCart(cartDTO)).thenReturn(cart);
		Mockito.doNothing().when(cartDaoMock).update(cart);
		Mockito.when(cartDaoMock.getAll()).thenReturn(carts);
		Mockito.when(cartDaoMock.getById(1)).thenReturn(cart);
		Mockito.when(cartDaoMock.createAndGetId(cart)).thenReturn(1);
	}
	
	@Test
	public void create() {
		cartServiceMock.create(cartDTO);
		Mockito.verify(cartDaoMock).create(cart);
	}
	
	@Test
	public void update() {
		cartServiceMock.update(cartDTO);
		Mockito.verify(cartDaoMock).update(cart);
	}
	
	@Test
	public void getById() {
		CartDTO cartDTO = cartServiceMock.getById(1);
		assertEquals(cartDTO, this.cartDTO);
	}
	
	@Test
	public void getByIdFail() {
		CartDTO cartDTO = cartServiceMock.getById(2);
		assertNull(cartDTO);
	}
	
	@Test
	public void getAll() {
		List<CartDTO> list = cartServiceMock.getAll();
		assertEquals(list, cartsDTO);
		Mockito.verify(cartDaoMock).getAll();
	}
	
	@Test
	public void createAndGetId() {
		int id = cartServiceMock.createAndGetId(cartDTO);
		assertEquals(1, id);
	}

}
