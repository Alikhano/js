package ru.alikhano.cyberlife.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.OrderDTO;


public interface OrderService {
	
	void create(OrderDTO orderDTO);

	List<OrderDTO> getAll();

	OrderDTO getById(int id);

	void update(OrderDTO orderDTO) throws IOException, TimeoutException;
	
	List<OrderDTO> getByCustomerId(int id);
	
	int createAndGetId(OrderDTO order);
	
	Map<Integer, Double> getMonthlyRevenue();
	
	double getWeeklyRevenue();
	
	String cartToOrder(OrderDTO orderDTO, CartDTO cartDTO, String username) throws CustomLogicException, IOException, TimeoutException;
	
	boolean isInTop(OrderDTO order);
	
	String changeOrderStatus(int orderId, String orderStatus, String paymentStatus) throws IOException, TimeoutException;

}
