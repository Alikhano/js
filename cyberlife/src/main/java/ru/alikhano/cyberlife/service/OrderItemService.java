package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface OrderItemService {
	
	/**
	 * creates new order item
	 * @param orderItemDTO
	 */
	void create(OrderItemDTO orderItemDTO);
	
	/**
	 * updates an existing order item
	 * @param orderItemDTO
	 */
	void update(OrderItemDTO orderItemDTO);

	/**
	 * deletes an order item
	 * @param orderItemDTO instance of the order item to be deleted
	 */
	void delete(OrderItemDTO orderItemDTO);

	/**
	 * deletes all order items from a specific order
	 * @param orderDTO instance of an order to be emptied
	 */
	void deleteAll(OrderDTO orderDTO);
	
	/**
	 * searches for a specific order item by id
	 * @param id of an order item that we're searching for
	 * @return instance of OrderItemDTO with a corresponding id
	 */
	OrderItemDTO getById(int id);


}
