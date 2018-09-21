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
		//find customer by username
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		
		//assign customer to order
		orderDTO.setCustomer(customerDTO);
		
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		
		//set price to order, erase price from cart
		orderDTO.setOrderPrice(cartDTO.getGrandTotal());
		cartDTO.setGrandTotal(0);
		//add date to order
		orderDTO.setOrderDate(new Date());


		//update order status
		orderDTO.setPaymentStatus("unpaid");
		orderDTO.setOrderStatus("awaits delivery");
		
		

		//set ordered items, erase cart items
		Set<CartItemDTO> items = cartDTO.getItems();
		
		int orderId = orderService.createAndGetId(orderDTO);
	
		
		for (CartItemDTO cartItem : items) {
			//decrease units in stock for product, update product
			ProductDTO productDTO = cartItem.getProduct();
			int prevQuantity = productDTO.getUnitsInStock();
			if (prevQuantity == 0) {
				model.addAttribute("noStockMsg", "Oops " + productDTO.getModel() + " is out of stock. Please remove from cart and proceed to order.");
				return "myOrder";
			}
			else {
				productDTO.setUnitsInStock(prevQuantity - cartItem.getQuantity());
				productService.update(productDTO);
							
			}
			
			//cartItem into orderItem
			OrderItemDTO item = new OrderItemDTO();
			item.setOrderQuantity(cartItem.getQuantity());
			item.setOrderTotal(cartItem.getTotalPrice());
			item.setProduct(cartItem.getProduct());
			orderService.getById(orderId).addItem(item);
			orderItemService.create(item);
			
		}
		
		cartItemService.deleteAll(cartDTO);
		items.clear();
	
		cartDTO.setItems(items);
		
		//update cart, create order
		cartService.update(cartDTO);
		


		
	
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
