package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;

public interface CartItemService {
	
	void create(CartItemDTO cartItemDTO);
	
	void create(ProductDTO productDTO, CartDTO cartDTO, CartItemDTO cartItemDTO);
	
	void update(CartItemDTO cartItemDTO);

	void delete(CartItemDTO cartItemDTO);

	void deleteAll(CartDTO cartDTO);
	
	CartItemDTO getById(int id);
	
	int checkCart(CartDTO cartDTO, ProductDTO productDTO);


}
