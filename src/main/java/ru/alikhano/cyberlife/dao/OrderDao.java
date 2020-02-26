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

	List<Orders> getOrdersByCustomerId(Integer id);

	Integer createAndGetId(Orders order);

	Map<Integer, Double> getMonthlyRevenue();

	Double getWeeklyRevenue();

	void merge(Orders order);

}
