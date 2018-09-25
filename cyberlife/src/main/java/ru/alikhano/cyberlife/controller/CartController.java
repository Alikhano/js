package ru.alikhano.cyberlife.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
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
	
	@RequestMapping("/myCart")
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
	
	@RequestMapping(value = "/deleteItem/{itemId}")
	public String deleteProduct(@PathVariable("itemId") int itemId, HttpServletRequest request, Model model) {
		double grandTotal = 0;
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		CartItemDTO cartItemDTO = cartItemService.getById(itemId);
		Set<CartItemDTO> items = cartDTO.getItems();
		Set<CartItemDTO> iterSet = new HashSet<>(items);
		for (CartItemDTO item : iterSet) {
			if (item.getItemId() == itemId) {
				items.remove(item);
			}
		}
		if (items.isEmpty()) {
			cartDTO.setGrandTotal(0);
		}
		else {
			grandTotal = cartDTO.getGrandTotal() - (cartItemDTO.getTotalPrice() * cartItemDTO.getQuantity());
		}
		
		cartDTO.setItems(items);
		cartDTO.setGrandTotal(grandTotal);
		cartService.update(cartDTO);
		cartItemService.delete(cartItemDTO);

		return "redirect:/myCart";
	}
	


}
