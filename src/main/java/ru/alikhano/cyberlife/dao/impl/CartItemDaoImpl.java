package ru.alikhano.cyberlife.dao.impl;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.model.CartItem;


@Repository
public class CartItemDaoImpl extends GenericDaoImpl<CartItem> implements CartItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void merge(CartItem cartItem) {
		 CartItem itemToSave = (CartItem) sessionFactory.getCurrentSession().merge(cartItem);
		  sessionFactory.getCurrentSession().save(itemToSave);
	}
	
	@Override
	public void delete(CartItem cartItem) {
		 CartItem itemToDelete = (CartItem) sessionFactory.getCurrentSession().merge(cartItem);
		 sessionFactory.getCurrentSession().delete(itemToDelete);
	}

}
