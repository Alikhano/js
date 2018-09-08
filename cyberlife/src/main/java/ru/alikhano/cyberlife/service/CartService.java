package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CartDTO;

public interface CartService {

	List<CartDTO> getAll();

	CartDTO getById(int id);

	void update(CartDTO cartDTO);

}
