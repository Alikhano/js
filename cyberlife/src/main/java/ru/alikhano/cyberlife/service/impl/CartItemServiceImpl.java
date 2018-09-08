package ru.alikhano.cyberlife.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemDao cartItemDao;

	@Autowired
	CartItemMapper cartItemMapper;

	@Autowired
	CartMapper cartMapper;

	@Override
	@Transactional
	public void create(CartItemDTO cartItemDTO) {
		cartItemDao.create(cartItemMapper.cartDTOtoCartItem(cartItemDTO));

	}

	@Override
	@Transactional
	public void delete(CartItemDTO cartItemDTO) {
		cartItemDao.delete(cartItemMapper.cartDTOtoCartItem(cartItemDTO));

	}

	@Override
	@Transactional
	public void deleteAll(CartDTO cartDTO) {
		Set<CartItem> items = cartDTO.getItems();
		for (CartItem item : items) {
			cartItemDao.delete(item);
		}
	}

	@Override
	@Transactional
	public void update(CartItemDTO cartItemDTO) {
		cartItemDao.update(cartItemMapper.cartDTOtoCartItem(cartItemDTO));
		
	}

}
