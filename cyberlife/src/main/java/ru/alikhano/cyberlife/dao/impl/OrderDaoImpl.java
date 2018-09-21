package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.model.Orders;

@Repository
public class OrderDaoImpl extends GenericDaoImpl<Orders> implements OrderDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> getOrdersByCustomerId(int id) {
		List<Orders> orders = sessionFactory.getCurrentSession().createQuery("from Orders where customerId =:id").setParameter("id", id).getResultList();
		return orders;
	}
	
	@Override
	public int createAndGetId(Orders order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
	}
	
	

}
