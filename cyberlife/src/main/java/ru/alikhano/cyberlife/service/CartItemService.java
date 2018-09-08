package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;

public interface CartItemService {
	
	void create(CartItemDTO cartItemDTO);
	
	void update(CartItemDTO cartItemDTO);

	void delete(CartItemDTO cartItemDTO);

	void deleteAll(CartDTO cartDTO);

}
