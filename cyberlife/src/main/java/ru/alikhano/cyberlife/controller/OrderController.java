package ru.alikhano.cyberlife.controller;

import java.util.Date;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.service.AddressService;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.OrderItemService;
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
	AddressService addressService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemService cartItemService;
		
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderItemService orderItemService;
	
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
	public String submitOrder(@Valid @ModelAttribute("newOrder") OrderDTO orderDTO, BindingResult result, HttpServletRequest request,Authentication authentication, Model model) {

		String username = authentication.getName();
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		
		String cartToOrder = orderService.cartToOrder(orderDTO, cartDTO, username);
		
		if (!cartToOrder.equals("success")) {
			model.addAttribute("noStockMsg", cartToOrder);
			return "/myOrder";
		}
		
		return "redirect:/myAccount";
		
	}
	
	@RequestMapping(value="/admin/orderStatus")
	public String orderStatus(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderService.getAll());
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}
	
	@RequestMapping(value="/orderHistory")
	public String myOrders(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderService.getAll());
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}
	
	@RequestMapping(value="/admin/orderStatus", method=RequestMethod.POST)
	public String orderStatusPost(@RequestParam("orderId") int orderId, @RequestParam("orderStatus") String orderStatus, @RequestParam("paymentStatus") String paymentStatus,Model model, HttpServletRequest request) {
		OrderDTO order = orderService.getById(orderId);
		order.setOrderStatus(orderStatus);
		order.setPaymentStatus(paymentStatus);
		orderService.update(order);
		
		return "redirect:/admin/admin-home";
	}
	

}
