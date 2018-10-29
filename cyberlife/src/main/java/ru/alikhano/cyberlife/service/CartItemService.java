package ru.alikhano.cyberlife.service;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;

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
	
	/**
	 * @param id of a cart item to be retrieved from the database
	 * @return instance of CartItemDTO with corresponding id
	 */
	CartItemDTO getById(int id);
	
	/**
	 * checks whether a specific product has already been added to cart
	 * @param cartDTO instance of cart to be checked 
	 * @param productDTO instance of a product that might have already been added to cart
	 * @return cart item id or 0 (if no such cart item has been created yet)
	 */
	int checkCart(CartDTO cartDTO, ProductDTO productDTO);
	
	
	/**
	 * deletes an item from cart
	 * @param itemId id of a cart item to be deleted
	 * @param cartId id of a cart to be edited
	 */
	void deleteFromCart(int itemId, int cartId);



}
