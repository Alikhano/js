package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Cart;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface CartDao extends GenericDao<Cart>{
	
	/** 
	 * @param instance of Cart to add to database
	 * @return id of new cart
	 */
	int createAndGetId(Cart cart);
	
	/** 
	 * saves modified cart instance to database
	 * @param instance of Cart to save after update
	 */
	void merge(Cart cart);

	

}
