package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ru.alikhano.cyberlife.dto.CartDTO;

import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private static final Logger LOGGER = LogManager.getLogger(CartServiceImpl.class);
	
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
			CartDTO cartDTO = cartMapper.forward(cart);
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
		return cartMapper.forward(cartDao.getById(id));
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
		cartDao.create(cartMapper.backward(cartDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public int createAndGetId(CartDTO cartDTO) {
		return cartDao.createAndGetId(cartMapper.backward(cartDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void merge(CartDTO cartDTO) {
		cartDao.merge(cartMapper.backward(cartDTO));
	}

	@Override
	public void deleteItemFromCart(CartDTO cartDTO, int itemId) {
		if (cartDTO == null) {
			LOGGER.info("Cart is null");
			return;
		}
		Set<CartItemDTO> items = cartDTO.getItems();
		if (CollectionUtils.isEmpty(items)) {
			LOGGER.info("Empty cart - nothing to delete");
			return;
		}
		for (CartItemDTO item : items) {
			if (item.getItemId() == itemId) {
				items.remove(item);
				cartItemService.delete(item);
				cartDTO.setGrandTotal(cartDTO.getGrandTotal() - item.getTotalPrice());
			}
		}
		if (CollectionUtils.isEmpty(items)) {
			cartDTO.setGrandTotal(0.0);
		}
		merge(cartDTO);
	}
}
