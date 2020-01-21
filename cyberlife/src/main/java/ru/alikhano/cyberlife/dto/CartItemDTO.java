package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.CartItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	
	private int itemId;
	private double totalPrice;
	private int quantity;
	private ProductDTO product;
	private CartDTO cart;

	public CartItemDTO(CartItem cartItem) {
		this.itemId = cartItem.getItemId();
		this.totalPrice = cartItem.getTotalPrice();
		this.quantity = cartItem.getQuantity();

	}
}
