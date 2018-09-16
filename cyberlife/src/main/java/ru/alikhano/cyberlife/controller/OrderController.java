package ru.alikhano.cyberlife.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.service.AddressService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.ProductService;
import ru.alikhano.cyberlife.service.UserService;

@Controller
public class OrderController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/myOrder")
	public String viewOrder(Model model, Authentication authentication, HttpServletRequest request) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("customer", customerDTO);
		
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		model.addAttribute("cart", cartDTO);
		
		model.addAttribute("cartItems", cartDTO.getItems());
		
		model.addAttribute("newOrder", new OrderDTO());
		
		return "myOrder";
	}
	
	@RequestMapping(value="/myOrder", method=RequestMethod.POST)
	public String submitOrder(@Valid @ModelAttribute("newOrder") OrderDTO orderDTO, BindingResult result, HttpServletRequest request,Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		orderDTO.setCustomer(customerDTO);
		
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		orderDTO.setCart(cartDTO);
		
		orderDTO.setPaymentStatus("unpaid");
		orderDTO.setOrderStatus("awaits delivery");

		Set<CartItemDTO> items = cartDTO.getItems();
		for (CartItemDTO item : items) {
			ProductDTO productDTO = item.getProduct();
			int prevQuantity = productDTO.getUnitsInStock();
			productDTO.setUnitsInStock(prevQuantity - item.getQuantity());
			productService.update(productDTO);
			
		}
		cartDTO.setItems(items);
		cartService.update(cartDTO);
		orderService.create(orderDTO);
		
		return "redirect:/myAccount";
		
	}
	
	@RequestMapping(value="/admin/orderStatus")
	public String orderStatus(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderService.getAll());
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}
	

}
