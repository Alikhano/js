package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alikhano.cyberlife.dto.CartDTO;

import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartDao cartDao;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private CartItemService cartItemService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<CartDTO> getAll() {
		List<CartDTO> cartsDTO = new ArrayList<>();
		cartDao.getAll().forEach(cart -> {
			CartDTO cartDTO = cartMapper.cartToCartDTO(cart);
			cartsDTO.add(cartDTO);
		});
		
		return cartsDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CartDTO getById(int id) {
		return cartMapper.cartToCartDTO(cartDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(CartDTO cartDTO) {

		merge(cartDTO);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(CartDTO cartDTO) {

		cartDao.create(cartMapper.cartDTOtoCart(cartDTO));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public int createAndGetId(CartDTO cartDTO) {

		return cartDao.createAndGetId(cartMapper.cartDTOtoCart(cartDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void merge(CartDTO cartDTO) {

		cartDao.merge(cartMapper.cartDTOtoCart(cartDTO));
	}

	@Override
	public void deleteItemFromCart(CartDTO cartDTO, int itemId) {
		Set<CartItemDTO> items = cartDTO.getItems();
		for (CartItemDTO item : items) {
			if (item.getItemId() == itemId) {
				items.remove(item);
				double grandTotal = cartDTO.getGrandTotal() - item.getTotalPrice();
				cartItemService.delete(item);
				cartDTO.setItems(items);
				cartDTO.setGrandTotal(grandTotal);
			}
		}
		if (items.isEmpty()) {
			cartDTO.setGrandTotal(0);
		}
		merge(cartDTO);
	}
}
