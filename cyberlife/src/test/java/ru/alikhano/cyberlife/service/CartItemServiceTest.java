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
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.impl.CartItemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CartItemServiceTest {

	@Mock
	private CartItemDao cartItemDaoMock;

	@Mock
	private CartItemMapper cartItemMapper;

	@InjectMocks
	private CartItemServiceImpl cartItemServiceMock;

	private CartItem cartItemMock;
	private Set<CartItem> itemsMock;
	private List<CartItem> itemsMockList;
	private Set<CartItemDTO> itemsDTOMock;
	private Cart cartMock;
	private CartDTO cartDTOMock;
	private ProductDTO productDTOMock;
	private CartItemDTO itemDTO;

	@Before
	public void init() {
		Category category = new Category(1, "education");
		Consciousness cons = new Consciousness(1, "middle AI", "nothing special");
		Product productMock = new Product(1, "rk800", "test description", 5, 1500.0, category, cons);
		ProductDTO productDTOMock = new ProductDTO(productMock);
		cartMock = new Cart(1, 1500.0, itemsMock);
		cartDTOMock = Mockito.mock(CartDTO.class);
		cartItemMock = new CartItem(1, 1, 1500.0, productMock, cartMock);
		itemDTO = Mockito.mock(CartItemDTO.class);
		itemDTO.setProduct(productDTOMock);
		itemsMock = new HashSet<>();
		itemsDTOMock = new HashSet<>();
		itemsMockList = new ArrayList<>();
		itemsMock.add(cartItemMock);
		itemsMockList.add(cartItemMock);
		itemsDTOMock.add(itemDTO);

		productDTOMock = Mockito.mock(ProductDTO.class);
		Mockito.when(cartItemDaoMock.getById(1)).thenReturn(cartItemMock);
		Mockito.when(cartItemMapper.cartItemToCartItemDTO(cartItemMock)).thenReturn(itemDTO);
		Mockito.when(cartItemMapper.cartDTOtoCartItem(itemDTO)).thenReturn(cartItemMock);
		Mockito.doNothing().when(cartItemDaoMock).update(cartItemMock);
		Mockito.doNothing().when(cartItemDaoMock).delete(cartItemMock);
		Mockito.doReturn(itemsDTOMock).when(cartDTOMock).getItems();
	}

	@Test
	public void create() {
		CartItem cartItem = new CartItem();
		CartItemDTO itemDTO = new CartItemDTO(cartItem);
		cartItemServiceMock.create(itemDTO);
		cartItemDaoMock.create(cartItem);

	}

	@Test
	public void update() {
		cartItemServiceMock.update(itemDTO);
		Mockito.verify(cartItemDaoMock).update(cartItemMock);
	}

	@Test
	public void delete() {
		cartItemServiceMock.delete(itemDTO);
		Mockito.verify(cartItemDaoMock).delete(cartItemMock);
	}

	@Test
	public void getById() {
		CartItemDTO cartItemDTO = cartItemServiceMock.getById(1);
		assertEquals(cartItemDTO, itemDTO);
	}

	@Test
	public void getByIdFail() {
		CartItemDTO cartItemDTO = cartItemServiceMock.getById(2);
		assertNull(cartItemDTO);
	}

	@Test
	public void deleteAll() {
		cartItemServiceMock.deleteAll(cartDTOMock);

		Mockito.verify(cartItemDaoMock).delete(cartItemMock);
	}

	@Test
	public void getCartItemById() {
		CartItemDTO cartItem = cartItemServiceMock.getCartItemById(cartDTOMock, 1);
		assertEquals(1,cartItem.getItemId());
	}

	@Test
	public void getCartItemByIdFail() {
		CartItemDTO cartItem = cartItemServiceMock.getCartItemById(cartDTOMock, 2);
		assertNull(cartItem);
	}
}
