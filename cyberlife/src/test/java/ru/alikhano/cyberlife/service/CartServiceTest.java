package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

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

import ru.alikhano.cyberlife.DTO.CartDTO;
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
	CartDao cartDaoMock;
	
	@Mock
	CartMapper cartMapper;
	
	@InjectMocks
	CartServiceImpl cartServiceMock;
	
	Cart cartMock;
	CartDTO cartDTOMock;
	List<Cart> cartsMock;
	List<CartDTO> cartsDTOMock;
	
	@Before
	public void init() {
		Category category = new Category(1,"education");
		Consciousness cons = new Consciousness(1, "middle AI", "nothing special");
		Product product = new Product(1,"rk800", "test description", 5, 1500.0, category, cons);
		CartItem item = new CartItem(1, 1, 1500.0, product);
		Set<CartItem> items = new HashSet<>();
		items.add(item);
		cartMock = new Cart(1, 1500.0, items);
		cartDTOMock = new CartDTO(cartMock);
		cartsMock = new ArrayList<>();
		cartsDTOMock = new ArrayList<>();
		cartsMock.add(cartMock);
		cartsDTOMock.add(cartDTOMock);
		Mockito.when(cartMapper.cartToCartDTO(cartMock)).thenReturn(cartDTOMock);
		Mockito.when(cartMapper.cartDTOtoCart(cartDTOMock)).thenReturn(cartMock);
		//Mockito.doNothing().when(cartDaoMock).create(cartMock);
		Mockito.when(cartDaoMock.getAll()).thenReturn(cartsMock);
		Mockito.when(cartDaoMock.getById(1)).thenReturn(cartMock);
		Mockito.when(cartDaoMock.createAndGetId(cartMock)).thenReturn(1);

	}
	
	@Test
	public void create() {
		Cart cart = new Cart();
		CartDTO cartDTO = new CartDTO(cart);
		cartServiceMock.create(cartDTO);
		cartDaoMock.create(cart);
		
	}
	
	@Test
	public void getById() {
		CartDTO cartDTO = cartServiceMock.getById(1);
		assertEquals(cartDTO, cartDTOMock);
	}
	
	@Test
	public void getAll() {
		List<CartDTO> list = cartServiceMock.getAll();
		assertEquals(list, cartsDTOMock);
		Mockito.verify(cartDaoMock).getAll();
	}
	
	@Test
	public void createAndGetId() {
		int id = cartServiceMock.createAndGetId(cartDTOMock);
		assertEquals(id, 1);
	}
	
	
	
	

}
