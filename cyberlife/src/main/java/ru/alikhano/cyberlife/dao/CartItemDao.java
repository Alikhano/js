package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.CartItem;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface CartItemDao extends GenericDao<CartItem> {
	
	/** 
	 * saves an updated cart item entry
	 * @param instance of CartItem to be updated
	 */
	void merge(CartItem cartItem);
}
