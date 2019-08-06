package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.dto.CartDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface CartService {

	/**
	 * @param cartDTO instance of CartDto to convert to Cart and save to the database
	 */
	void create(CartDTO cartDTO);

	/**
	 * @return list of all existing carts
	 */
	List<CartDTO> getAll();

	/**
	 * @param id id of the cart that the user is searching for
	 * @return cart with the matching id
	 */
	CartDTO getById(int id);

	/**
	 * @param cartDTO cart to be updated
	 */
	void update(CartDTO cartDTO);

	/**
	 * @param cartDTO instance of CartDto to convert to Cart and save to the database
	 * @return id of a new cart entry
	 */
	int createAndGetId(CartDTO cartDTO);

    /**
     * @param cartDTO
     */
	void merge(CartDTO cartDTO);

}
