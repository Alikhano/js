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
public interface CartDao extends GenericDao<Cart> {

	Integer createAndGetId(Cart cart);

	void merge(Cart cart);

}
