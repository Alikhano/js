package ru.alikhano.cyberlife.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.Orders;

@Repository
public interface OrderDao extends GenericDao<Orders> {
	
	List<Orders> getOrdersByCustomerId(int id);
	
	public int createAndGetId(Orders order);

}
