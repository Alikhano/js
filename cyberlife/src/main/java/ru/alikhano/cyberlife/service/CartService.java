package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;

public interface CartService {
	
	void create(CartDTO cartDTO);

	List<CartDTO> getAll();

	CartDTO getById(int id);

	void update(CartDTO cartDTO);
	
	int createAndGetId(CartDTO cartDTO);
	
	CartItemDTO getCartItemById(CartDTO cartDTO, int id);

	void merge(CartDTO cartDTO);
	

}
