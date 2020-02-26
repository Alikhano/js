package ru.alikhano.cyberlife.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.service.CartService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	private static final Logger LOGGER = LogManager.getLogger(CartController.class);

	@GetMapping("/myCart")
	public String viewCart(HttpServletRequest request, Model model) {
		int cartId = 0;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("cartId")) {
				cartId = Integer.parseInt(cookie.getValue());
			}
		}
		if (cartId != 0) {
			CartDTO cartDTO = cartService.getById(cartId);
			model.addAttribute("cart", cartDTO);
			model.addAttribute("cartItems", cartDTO.getItems());
		}
		
		return "cartList";	
	}

	@GetMapping(value = "/deleteItem/{itemId}")
	public String deleteProduct(@PathVariable("itemId") int itemId, HttpServletRequest request, Model model){
		int cartId = Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue());
		CartDTO cartDTO = cartService.getById(cartId);
	    cartService.deleteItemFromCart(cartDTO, itemId);
				
		LOGGER.info("Item is removed from cart");

		return "redirect:/myCart";
	}
}
