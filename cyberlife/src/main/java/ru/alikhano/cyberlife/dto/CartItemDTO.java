package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
	
	private Integer itemId;
	private Double totalPrice;
	private Integer quantity;
	private ProductDTO product;
	private CartDTO cart;

}
