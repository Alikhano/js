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

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.service.impl.CartItemServiceImpl;
import ru.alikhano.cyberlife.supplier.CartItemSupplier;
import ru.alikhano.cyberlife.supplier.CartSupplier;
import ru.alikhano.cyberlife.supplier.ProductSupplier;

@RunWith(MockitoJUnitRunner.class)
public class CartItemServiceTest {

	@Mock
	private CartItemDao cartItemDao;
	@Mock
	private CartItemMapper cartItemMapper;

	@InjectMocks
	private CartItemServiceImpl cartItemService;

	private CartItem cartItem;
	private CartDTO cartDTO;
	private CartDTO cartDTOWithEmptyItems;
	private CartItemDTO cartItemDTO;
	private ProductDTO  productDTONew;
	private ProductDTO productDTOExisting;

	@Before
	public void init() {
		productDTOExisting = ProductSupplier.getProductDTO(1, "rk800");
		productDTONew = ProductSupplier.getProductDTO(2, "rk900");
		cartItem = CartItemSupplier.getCartItemWithSetProduct();
		cartItemDTO = CartItemSupplier.getCartItemDTOWithSetProduct();
		cartDTO = CartSupplier.getCartDTOWithSpecifiedCartItems(cartItemDTO);
		cartDTOWithEmptyItems = CartSupplier.getEmptyCartDTO();


		Mockito.when(cartItemDao.getById(1)).thenReturn(cartItem);
		Mockito.when(cartItemMapper.cartItemToCartItemDTO(cartItem)).thenReturn(cartItemDTO);
		Mockito.when(cartItemMapper.cartDTOtoCartItem(cartItemDTO)).thenReturn(cartItem);
		Mockito.doNothing().when(cartItemDao).create(cartItem);
		Mockito.doNothing().when(cartItemDao).update(cartItem);
		Mockito.doNothing().when(cartItemDao).delete(cartItem);
	}

	@Test
	public void createNewCartItem() {
		cartItemService.create(productDTONew, cartDTO, cartItemDTO);
		Mockito.verify(cartItemDao).create(cartItem);
	}

	@Test
	public void creatNewCartItemWithEmptyCart() {
		cartItemService.create(productDTONew, cartDTOWithEmptyItems, cartItemDTO);
		Mockito.verify(cartItemDao).create(cartItem);
	}

	@Test
	public void updateExistingCartItem() {
		cartItemService.create(productDTOExisting, cartDTO, cartItemDTO);
		Mockito.verify(cartItemDao).update(cartItem);
	}

	@Test
	public void delete() {
		cartItemService.delete(cartItemDTO);
		Mockito.verify(cartItemDao).delete(cartItem);
	}

	@Test
	public void getById() {
		CartItemDTO cartItemDTO = cartItemService.getById(1);
		assertEquals(cartItemDTO, this.cartItemDTO);
	}

	@Test
	public void getByIdFail() {
		CartItemDTO cartItemDTO = cartItemService.getById(2);
		assertNull(cartItemDTO);
	}

	@Test
	public void deleteAll() {
		cartItemService.deleteAll(cartDTO);
		Mockito.verify(cartItemDao).delete(cartItem);
	}

	@Test
	public void getCartItemById() {
		CartItemDTO cartItem = cartItemService.getCartItemById(cartDTO, 1);
		assertEquals(1,cartItem.getItemId());
	}

	@Test
	public void getCartItemByIdFail() {
		CartItemDTO cartItem = cartItemService.getCartItemById(cartDTO, 2);
		assertNull(cartItem);
	}
}
