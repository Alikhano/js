package ru.alikhano.cyberlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User getByUsername(String username) {
		return (User) sessionFactory.getCurrentSession().createQuery("from User where username = :username").setParameter("username", username).uniqueResult();
	}

}
