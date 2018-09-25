package ru.alikhano.cyberlife.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
		return sessionFactory.getCurrentSession().createQuery("from Orders where customerId =:id")
				.setParameter("id", id).getResultList();

	}

	@Override
	public int createAndGetId(Orders order) {
		return (Integer) sessionFactory.getCurrentSession().save(order);
	}

	@Override
	public Map<Integer, Double> getMonthlyRevenue() {
		String hql = "select month(orderDate), sum(orderPrice)  from Orders where paymentStatus =: paymentStatus " +  "GROUP BY month(orderDate), YEAR(orderDate)";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("paymentStatus", "paid");
		Map<Integer, Double> monthlyRev = new HashMap<>();
		
		for (Object o : query.list()) {
			Object[] row = (Object[]) o;
			monthlyRev.put((Integer)row[0], (Double)row[1]);

		}

		return monthlyRev;
	}

	@Override
	public double getWeeklyRevenue() {
		Calendar date = Calendar.getInstance();
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			date.add(Calendar.DAY_OF_MONTH, -1);
			Date yesterday = date.getTime();
			date.add(Calendar.DAY_OF_MONTH, -7);
			Date weekBefore = date.getTime();
			String hql = "SELECT sum(orderPrice) FROM Orders WHERE paymentStatus =: paymentStatus AND orderDate BETWEEN ?1 AND ?2";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("paymentStatus", "paid");
			query.setParameter(1, weekBefore);
			query.setParameter(2, yesterday);
			return (double) query.uniqueResult();
			
		}
		
		return 0;		
	}

}
