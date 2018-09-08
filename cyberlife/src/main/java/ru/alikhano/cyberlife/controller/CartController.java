package ru.alikhano.cyberlife.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.ProductService;

@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cartItemService;
	
	public void addToCart(@PathVariable("productId") int productId, @PathVariable("quantity") int quantity) {
		CartDTO cartDTO = new CartDTO();
		CartItemDTO cartItemDTO = new CartItemDTO();
		ProductDTO productDTO = productService.getById(productId);
		cartItemDTO.setProduct(productDTO);
		cartItemDTO.setQuantity(quantity);
		cartItemDTO.setTotalPrice(productDTO.getPrice() * quantity);
		cartItemService.create(cartItemDTO);
		Set<CartItemDTO> items = cartDTO.getItems();
		items.add(cartItemDTO);
		cartService.create(cartDTO);
		
		
	
	}

}
