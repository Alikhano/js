package ru.alikhano.cyberlife.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.CartDao;
import ru.alikhano.cyberlife.model.Cart;


@Repository
public class CartDaoImpl extends GenericDaoImpl<Cart> implements CartDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public int createAndGetId(Cart cart) {
		return (Integer) sessionFactory.getCurrentSession().save(cart);
	}

}
