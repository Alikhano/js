package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.OrderDTO;


public interface OrderService {
	
	void create(OrderDTO orderDTO);

	List<OrderDTO> getAll();

	OrderDTO getById(int id);

	void update(OrderDTO orderDTO);
	
	List<OrderDTO> getByCustomerId(int id);

}
