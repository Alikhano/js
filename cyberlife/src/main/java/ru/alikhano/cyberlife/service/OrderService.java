package ru.alikhano.cyberlife.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.OrderDTO;


/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface OrderService {
	
	/**
	 * creates new order
	 * @param orderDTO
	 */
	void create(OrderDTO orderDTO);

	/**
	 * @return a list of all existing orders
	 */
	List<OrderDTO> getAll();

	/** 
	 * searches for a specific order by id
	 * @param id of an order that we're searching for
	 * @return instance of OrderDTO with corresponding id
	 */
	OrderDTO getById(int id);

	/**
	 * updates an existing order
	 * @param orderDTO
	 * @throws IOException
	 * @throws TimeoutException
	 */
	void update(OrderDTO orderDTO) throws IOException, TimeoutException;
	
	/**
	 * @param id of a customer who created the orders
	 * @return list of orders created by a specific customer
	 */
	List<OrderDTO> getByCustomerId(int id);
	
	/** 
	 * creates new order and returns it's id
	 * @param order
	 * @return id of a newly created order
	 */
	int createAndGetId(OrderDTO order);
	
	/**
	 * calculates monthly revenue
	 * @return number of a months, amount of monthly revenue in a hashmap
	 */
	Map<Integer, Double> getMonthlyRevenue();
	
	/**
	 * @return amount of a weekly revenue
	 */
	double getWeeklyRevenue();
	
	/**
	 * converts cart entry to order
	 * @param orderDTO instance of new order
	 * @param cartDTO instance of cart to be converted
	 * @param username username to identify the customer
	 * @return conversion status (success/fail)
	 * @throws CustomLogicException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	String cartToOrder(OrderDTO orderDTO, CartDTO cartDTO, String username) throws CustomLogicException, IOException, TimeoutException;
	
	/**
	 * @param order
	 * @return true/false depending on whether products from the order are present in the top 10 list
	 */
	boolean isInTop(OrderDTO order);
	
	/**
	 * changes order status/payment status (administrator functionality)
	 * @param orderId id of an order to be changed
	 * @param orderStatus new order status
	 * @param paymentStatus new payment status
	 * @return result of the operation (success/fail message)
	 * @throws IOException
	 * @throws TimeoutException
	 */
	String changeOrderStatus(int orderId, String orderStatus, String paymentStatus) throws IOException, TimeoutException;

}
