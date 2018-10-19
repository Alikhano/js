package ru.alikhano.cyberlife.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.OrderDTO;
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

	private static final Logger logger = LogManager.getLogger(OrderController.class);

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

	@RequestMapping(value = "/myOrder", method = RequestMethod.POST)
	public String submitOrder(@Valid @ModelAttribute("newOrder") OrderDTO orderDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model)
			throws CustomLogicException, IOException, TimeoutException {

		String username = authentication.getName();
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));

		String cartToOrder = orderService.cartToOrder(orderDTO, cartDTO, username);

		if (orderDTO.getPaymentType().equals("credit card")) {
			request.getSession().setAttribute("totalPrice", orderDTO.getOrderPrice());
			request.getSession().setAttribute("orderId", orderDTO.getOrderId());
			return "redirect:/myOrder/cardPayment";
		}

		if (!cartToOrder.equals("success")) {
			model.addAttribute("noStockMsg", cartToOrder);
			logger.info(username + " has created new order");
			return "/myOrder";
		}

		return "redirect:/myAccount";

	}

	@RequestMapping(value = "/admin/orderStatus")
	public String orderStatus(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderService.getAll());
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}

	@RequestMapping(value = "/orderHistory")
	public String myOrders(Model model, HttpServletRequest request, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("orders", orderService.getByCustomerId(customerDTO.getCustomerId()));
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}

	@RequestMapping(value = "/admin/orderStatus", method = RequestMethod.POST)
	public String orderStatusPost(@RequestParam("orderId") int orderId, @RequestParam("orderStatus") String orderStatus,
			@RequestParam("paymentStatus") String paymentStatus, Model model, Authentication authentication,
			HttpServletRequest request) throws CustomLogicException, IOException, TimeoutException {

		OrderDTO order = orderService.getById(orderId);
		if (order.getOrderStatus().equals("delivered and recieved") && order.getPaymentStatus().equals("paid")) {
			model.addAttribute("errorOrder", "No status updates after order completion!");
			logger.error("Modifications after order completion");
			model.addAttribute("updatedOrder", new OrderDTO());
			model.addAttribute("orders", orderService.getAll());
			return "orderList";
		}

		if (orderStatus.equals("order status")) {
			order.setPaymentStatus(paymentStatus);
			orderService.update(order);
		} else if (paymentStatus.equals("payment status")) {
			order.setOrderStatus(orderStatus);
			orderService.update(order);
		} else {
			order.setOrderStatus(orderStatus);
			order.setPaymentStatus(paymentStatus);
			orderService.update(order);
		}

		logger.info("Admin has updated the order");

		return "redirect:/admin/stats";
	}

	@GetMapping(value = "/myOrder/cardPayment")
	public String creditCardPayment(Authentication authentication, Model model,
			HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute("orderId");
		OrderDTO orderDTO = orderService.getById(id);

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("total", (double) request.getSession().getAttribute("totalPrice"));
		return "cardPayment";
	}

	@PostMapping(value = "/myOrder/cardPayment")
	public String creditCardPaymentPost(@ModelAttribute OrderDTO orderDTO, HttpServletRequest request, Model model) throws IOException, TimeoutException {
	
		orderDTO.setPaymentStatus("paid");
		orderService.update(orderDTO);
		return "redirect:/orderHistory";

	}

}
