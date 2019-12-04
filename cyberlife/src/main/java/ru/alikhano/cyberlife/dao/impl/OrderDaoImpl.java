package ru.alikhano.cyberlife.dao.impl;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
	private SessionFactory sessionFactory;

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
		String hql = "select month(orderDate), sum(orderPrice)  from Orders where paymentStatus =: paymentStatus "
				+  "GROUP BY month(orderDate), YEAR(orderDate)";
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
		LocalDate localDate = LocalDate.now();
		if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
			LocalDate yesterday = localDate.minus(Period.ofDays(1));
			LocalDate weekBefore = localDate.minus(Period.ofWeeks(1));
			String hql = "SELECT sum(orderPrice) FROM Orders WHERE paymentStatus =: paymentStatus " +
					"AND orderDate BETWEEN ?1 AND ?2";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("paymentStatus", "paid");
			query.setParameter(1, Date.from(weekBefore.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			query.setParameter(2, Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			return (query.uniqueResult() == null) ? 0: (double) query.uniqueResult();
		}
		
		return 0;		
	}


	@Override
	public void merge(Orders order) {
		 Orders orderToSave = (Orders) sessionFactory.getCurrentSession().merge(order);
		 sessionFactory.getCurrentSession().save(orderToSave);
	}

}
