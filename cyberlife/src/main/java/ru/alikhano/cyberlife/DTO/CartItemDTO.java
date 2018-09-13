package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.Product;

public class CartItemDTO {
	
	private int itemId;
	private double totalPrice;
	private int quantity;
	private ProductDTO product;
	private CartDTO cart;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public CartDTO getCart() {
		return cart;
	}
	public void setCart(CartDTO cart) {
		this.cart = cart;
	}
	
	
	

}
