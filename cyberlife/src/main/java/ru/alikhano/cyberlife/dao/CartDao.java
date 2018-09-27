package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Cart;

@Repository
public interface CartDao extends GenericDao<Cart>{
	
	int createAndGetId(Cart cart);

	

}
