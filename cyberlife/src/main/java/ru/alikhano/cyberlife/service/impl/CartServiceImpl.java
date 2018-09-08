package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	CartMapper cartMapper;

	@Override
	@Transactional
	public List<CartDTO> getAll() {
		List<Cart> carts = cartDao.getAll();
		List<CartDTO> cartsDTO = new ArrayList<>();
		carts.stream().forEach(cart -> {
			CartDTO cartDTO = cartMapper.cartToCartDTO(cart);
			cartsDTO.add(cartDTO);
		});
		
		return cartsDTO;
	}

	@Override
	@Transactional
	public CartDTO getById(int id) {
		return cartMapper.cartToCartDTO(cartDao.getById(id));
	}

	@Override
	@Transactional
	public void update(CartDTO cartDTO) {
		cartDao.update(cartMapper.cartDTOtoCart(cartDTO));
		
	}

	@Override
	public void create(CartDTO cartDTO) {
		cartDao.create(cartMapper.cartDTOtoCart(cartDTO));
		
	}

}
