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

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.ProductService;
import ru.alikhano.cyberlife.service.UserService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemService cartItemService;
	
	private static final Logger logger = LogManager.getLogger(CartController.class);
	
	/**
	 * controller to show user's cart entries
	 * @param request http request recieved from client side
	 * @param authentication to retrieve customer's username
	 * @return jsp file name
	 */
	@GetMapping("/myCart")
	public String viewCart(HttpServletRequest request, Model model) {
		int cartId = 0;
		CartDTO cartDTO = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("cartId")) {
				cartId = Integer.parseInt(cookie.getValue());
			}
		}
		if (cartId != 0) {
			cartDTO = cartService.getById(cartId);
			model.addAttribute("cart", cartDTO);
			model.addAttribute("cartItems", cartDTO.getItems());
		}
		
		
		return "cartList";	
	}
	
	/**
	 * removes product from cart
	 * @param itemId - id of the item to be removed from the cart
	 * @param request http request received from client side
	 * @param model
	 * @return jsp file name
	 */
	@GetMapping(value = "/deleteItem/{itemId}")
	public String deleteProduct(@PathVariable("itemId") int itemId, HttpServletRequest request, Model model){
		
		int cartId = Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue());
	    cartItemService.deleteFromCart(itemId, cartId);
				
		logger.info("Item is removed from cart");

		return "redirect:/myCart";
	}
	


}
