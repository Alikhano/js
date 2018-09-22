package ru.alikhano.cyberlife.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Orders;

@Repository
public interface OrderDao extends GenericDao<Orders> {
	
	List<Orders> getOrdersByCustomerId(int id);
	
	int createAndGetId(Orders order);
	
	Map<Integer, Double> getMonthlyRevenue();
	
	double getWeeklyRevenue();

}
