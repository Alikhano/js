package ru.alikhano.cyberlife.dto;

import java.util.Set;

import ru.alikhano.cyberlife.model.Cart;


public class CartDTO {
	
	private int cartId;
	private double grandTotal;
	private Set<CartItemDTO> items;
	
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	public Set<CartItemDTO> getItems() {
		return items;
	}
	public void setItems(Set<CartItemDTO> items) {
		this.items = items;
	}
	
	
	public CartDTO() {
	
	}
	public CartDTO(Cart cart) {
		
		this.cartId = cart.getCartId();
		this.grandTotal = cart.getGrandTotal();
	}
	
	
	

}
