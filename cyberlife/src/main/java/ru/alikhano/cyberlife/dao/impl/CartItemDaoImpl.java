package ru.alikhano.cyberlife.dao.impl;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;

@Repository
public class CartItemDaoImpl extends GenericDaoImpl<CartItem> implements CartItemDao {
	
	@Autowired
	SessionFactory sessionFactory;

}
