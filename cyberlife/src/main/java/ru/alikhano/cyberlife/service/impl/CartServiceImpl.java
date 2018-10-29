package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;

import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	CartItemService cartItemService;
	
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
		merge(cartDTO);
		
	}

	@Override
	@Transactional
	public void create(CartDTO cartDTO) {
		cartDao.create(cartMapper.cartDTOtoCart(cartDTO));
		
	}

	@Override
	@Transactional
	public int createAndGetId(CartDTO cartDTO) {
		return cartDao.createAndGetId(cartMapper.cartDTOtoCart(cartDTO));
	}

	@Override
	public CartItemDTO getCartItemById(CartDTO cartDTO, int id) {
		Set<CartItemDTO> items = cartDTO.getItems();
		
		for (CartItemDTO item : items) {
			if (item.getItemId() == id) {
				return item;
			}
		}
		
		return null;
		
	}
	
	@Override
	public void merge(CartDTO cartDTO) {
		cartDao.merge(cartMapper.cartDTOtoCart(cartDTO));
		
	}


	
	
	

}
