package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface CartItemService {
	
	/**
	 * @param cartItemDTO instance of CarItemDTO to convert to CartItem and add to the database
	 */
	void create(CartItemDTO cartItemDTO);
	
	/**
	 * @param productDTO Product instance to be added to cart
	 * @param cartDTO instance of a cart
	 * @param cartItemDTO CartItem instance that will reference the Product
	 */
	void create(ProductDTO productDTO, CartDTO cartDTO, CartItemDTO cartItemDTO);
	
	/**
	 * @param cartItemDTO updated instance of CartItemDTO to convert to CartItem and add to the database
	 */
	void update(CartItemDTO cartItemDTO);

	/**
	 * @param cartItemDTO instance of CartItemDTO to convert to CartItem and delete from the database
	 */
	void delete(CartItemDTO cartItemDTO);

	/**
	 * deletes all cart items from a specific cart
	 * @param cartDTO instance of cart to be emptied
	 */
	void deleteAll(CartDTO cartDTO);

}
