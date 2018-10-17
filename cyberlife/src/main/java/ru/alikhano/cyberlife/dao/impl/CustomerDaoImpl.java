package ru.alikhano.cyberlife.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.model.Customer;


@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	UserDao userDao;

	@Override
	public Customer getByUserId(int userId) {
		return (Customer) sessionFactory.getCurrentSession().createQuery("from Customer where userId = :userId")
				.setParameter("userId", userId).uniqueResult();
	}

	@Override
	public List<Customer> getTopCustomers() {

		List<Customer> topCustomers = new ArrayList<>();

		String hql = "select order.customer, count(*) as purchaseCount  from Orders order where order.paymentStatus =: paymentStatus "
				+ "GROUP BY order.customer ORDER BY purchaseCount desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("paymentStatus", "paid");
		query.setMaxResults(10);
		List<Object[]> resultList = query.list();

		for (Object o : resultList) {
			Object[] row = (Object[]) o;
			Customer customer = (Customer) row[0];
			topCustomers.add(customer);
		}

		return topCustomers;

	}

	@Override
	public Customer getByEmail(String email) {
		return (Customer) sessionFactory.getCurrentSession().createQuery("from Customer where email = :email")
				.setParameter("email", email).uniqueResult();
	}

}
