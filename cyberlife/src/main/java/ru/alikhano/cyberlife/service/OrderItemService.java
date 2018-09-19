package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;

public interface OrderItemService {
	
	void create(OrderItemDTO orderItemDTO);
	
	void update(OrderItemDTO orderItemDTO);

	void delete(OrderItemDTO orderItemDTO);

	void deleteAll(OrderDTO orderDTO);
	
	OrderItemDTO getById(int id);


}
