package ru.alikhano.cyberlife.mapper;

import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;

public class CartOrderMapper {
	
	public OrderItemDTO mapCartItemstoOrder(CartItemDTO cartItem) {
		OrderItemDTO item = new OrderItemDTO();
		item.setOrderQuantity(cartItem.getQuantity());
		item.setOrderTotal(cartItem.getTotalPrice());
		item.setProduct(cartItem.getProduct());
		
		return item;
	}

}
