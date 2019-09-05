package ru.alikhano.cyberlife.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemDao cartItemDao;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemMapper cartItemMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(CartItemDTO cartItemDTO) {
		cartItemDao.create(cartItemMapper.cartDTOtoCartItem(cartItemDTO));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(CartItemDTO cartItemDTO) {

		cartItemDao.delete(cartItemMapper.cartDTOtoCartItem(cartItemDTO));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteAll(CartDTO cartDTO) {
		for (CartItemDTO item : cartDTO.getItems()) {
			delete(item);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(CartItemDTO cartItemDTO) {

		cartItemDao.update(cartItemMapper.cartDTOtoCartItem(cartItemDTO));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CartItemDTO getById(int id) {

		return cartItemMapper.cartItemToCartItemDTO(cartItemDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(ProductDTO productDTO, CartDTO cartDTO, CartItemDTO cartItemDTO) {

		int itemId = checkCart(cartDTO, productDTO);

		if (itemId != 0) {
			CartItemDTO item = getCartItemById(cartDTO, itemId);
			updateExistingCartItem(cartDTO, item, productDTO);

		} else {
		   createNewCartItem(cartDTO, cartItemDTO, productDTO);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public int checkCart(CartDTO cartDTO, ProductDTO productDTO) {
		Set<CartItemDTO> items = cartDTO.getItems();

		if (!items.isEmpty()) {
			CartItemDTO matchingCartItem = items.stream()
					.filter(item -> item.getProduct().getProductId() == productDTO.getProductId())
					.findFirst().orElse(null);

			return matchingCartItem != null ? matchingCartItem.getItemId() : 0;

		}

		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteFromCart(int itemId, int cartId) {

		CartDTO cartDTO = cartService.getById(cartId);

		double grandTotal = 0;

		CartItemDTO cartItemDTO = getById(itemId);
		Set<CartItemDTO> items = cartDTO.getItems();
		Set<CartItemDTO> iterSet = new HashSet<>(items);
		for (CartItemDTO item : iterSet) {
			if (item.getItemId() == itemId) {
				items.remove(item);
			}
		}
		if (items.isEmpty()) {
			cartDTO.setGrandTotal(0);
		} else {
			grandTotal = cartDTO.getGrandTotal() - cartItemDTO.getTotalPrice();
		}

		cartDTO.setItems(items);
		cartDTO.setGrandTotal(grandTotal);
		cartService.merge(cartDTO);
		delete(cartItemDTO);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CartItemDTO getCartItemById(CartDTO cartDTO, int id) {
		for (CartItemDTO item : cartDTO.getItems()) {
			if (item.getItemId() == id) {
				return item;
			}
		}

		return null;
	}

	private void createNewCartItem(CartDTO cartDTO, CartItemDTO cartItemDTO, ProductDTO productDTO) {

		int quantity = cartItemDTO.getQuantity();

		double totalPrice = quantity * productDTO.getPrice();
		cartItemDTO.setTotalPrice(totalPrice);

		cartItemDTO.setProduct(productDTO);
		cartItemDTO.setCart(cartDTO);

		cartDTO.getItems().add(cartItemDTO);

		cartDTO.setGrandTotal(cartItemDTO.getTotalPrice() + cartDTO.getGrandTotal());

		create(cartItemDTO);

	}

	private void updateExistingCartItem(CartDTO cartDTO, CartItemDTO cartItemDTO, ProductDTO productDTO) {

		int quantity = cartItemDTO.getQuantity();

		double price = cartItemDTO.getTotalPrice();

		double newPrice = cartItemDTO.getQuantity() * productDTO.getPrice();
		cartItemDTO.setQuantity(quantity + cartItemDTO.getQuantity());

		cartItemDTO.setTotalPrice(price + newPrice);

		cartDTO.setGrandTotal(newPrice + cartDTO.getGrandTotal());
		update(cartItemDTO);
	}

}
