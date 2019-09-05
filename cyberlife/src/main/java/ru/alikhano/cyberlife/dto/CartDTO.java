package ru.alikhano.cyberlife.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.Cart;


@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
	
	private int cartId;
	private double grandTotal;
	private Set<CartItemDTO> items;

	public CartDTO(Cart cart) {
		
		this.cartId = cart.getCartId();
		this.grandTotal = cart.getGrandTotal();
	}
}
