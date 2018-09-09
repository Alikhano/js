package ru.alikhano.cyberlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.model.User;

@Repository
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	UserDao userDao;

	@Override
	public Customer getByUserId(int userId) {
		//User user = userDao.getById(userId);
		return (Customer) sessionFactory.getCurrentSession().createQuery("from Customer where userId = :userId").setParameter("userId", userId).uniqueResult();
	}

}
