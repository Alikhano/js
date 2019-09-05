package ru.alikhano.cyberlife.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Orders;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface OrderDao extends GenericDao<Orders> {
	
	/** 
	 * searches for order by id of a customer who created id
	 * @param id customer id
	 * @return list of orders created by a specific customer
	 */
	List<Orders> getOrdersByCustomerId(int id);
	
	/** 
	 * creates new order entry
	 * @param order Order instance to add to database
	 * @return id of new order
	 */
	int createAndGetId(Orders order);
	
	/** 
	 * calculates monthly revenue
	 * @return number of month, amount of monthly revenue in a hashmap
	 */
	Map<Integer, Double> getMonthlyRevenue();

	/** 
	 * calculates weekly revenue
	 * @return amount of weekly revenue
	 */
	double getWeeklyRevenue();
	
	/** 
	 * saves an updated order entry
	 * @param order instance of order to be updated
	 */
	void merge(Orders order);

}
