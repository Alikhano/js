package ru.alikhano.cyberlife.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.mapper.CartMapper;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemDao cartItemDao;
	
	@Autowired
	CartService cartService;

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
		Set<CartItemDTO> items = cartDTO.getItems();
		for (CartItemDTO item : items) {
			cartItemDao.delete(cartItemMapper.cartDTOtoCartItem(item));
		}
	}

	@Override
	@Transactional
	public void update(CartItemDTO cartItemDTO) {
		cartItemDao.update(cartItemMapper.cartDTOtoCartItem(cartItemDTO));
		
	}

	@Override
	@Transactional
	public CartItemDTO getById(int id) {
		return cartItemMapper.cartItemToCartItemDTO(cartItemDao.getById(id));
	}

	@Override
	@Transactional
	public void create(ProductDTO productDTO, CartDTO cartDTO, CartItemDTO cartItemDTO) {
		
		 
		Set<CartItemDTO> items = cartDTO.getItems();
		
		if (!items.isEmpty()) {
			int itemId = checkCart(cartDTO, productDTO);
			
			if (itemId != 0) {
			    CartItemDTO item = cartService.getCartItemById(cartDTO, itemId);
			    double price = item.getTotalPrice();
		        int quantity = item.getQuantity();
				item.setQuantity(quantity + cartItemDTO.getQuantity());
				
				item.setTotalPrice(price + cartItemDTO.getTotalPrice());
		
				cartDTO.setGrandTotal(cartItemDTO.getTotalPrice() + cartDTO.getGrandTotal());
				update(item);
			}
			
			else {
				int quantity = cartItemDTO.getQuantity();
		        
		        double totalPrice = quantity * productDTO.getPrice();
		        cartItemDTO.setTotalPrice(totalPrice);
		        
		        cartItemDTO.setProduct(productDTO);
		        
		        items.add(cartItemDTO);
		        cartDTO.setItems(items);
		        
		        cartDTO.setGrandTotal(cartItemDTO.getTotalPrice() + cartDTO.getGrandTotal());
		        cartItemDTO.setCart(cartDTO);
		        create (cartItemDTO);
		        System.out.println("ATTENTION!!!! NEW!!!" + cartItemDTO.getQuantity() + " " + cartItemDTO.getTotalPrice());
			}
		}
		else {
	     int quantity = cartItemDTO.getQuantity();
	        
	        double totalPrice = quantity * productDTO.getPrice();
	        cartItemDTO.setTotalPrice(totalPrice);
	        
	        cartItemDTO.setProduct(productDTO);
	        
	        items.add(cartItemDTO);
	        cartDTO.setItems(items);
	        
	        cartDTO.setGrandTotal(cartItemDTO.getTotalPrice() + cartDTO.getGrandTotal());
	        cartItemDTO.setCart(cartDTO);
	        create(cartItemDTO);
	        System.out.println("ATTENTION!!!! NEW!!!" + cartItemDTO.getQuantity() + " " + cartItemDTO.getTotalPrice());
	        
		}
	        
		
	}

	@Override
	@Transactional
	public int checkCart(CartDTO cartDTO, ProductDTO productDTO) {
		Set<CartItemDTO> items = cartDTO.getItems();
		
		for (CartItemDTO item : items) {
			if (item.getProduct().getProductId() == productDTO.getProductId()) {
				return item.getItemId();
			}
		}
		
		return 0;
		
	}

}
