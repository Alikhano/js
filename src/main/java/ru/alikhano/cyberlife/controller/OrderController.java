package ru.alikhano.cyberlife.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;
import ru.alikhano.cyberlife.mapper.OrderStatusMapper;
import ru.alikhano.cyberlife.mapper.PaymentStatusMapper;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.UserService;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Controller
public class OrderController {

    private static final Logger LOGGER = LogManager.getLogger(OrderController.class);

    @Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PaymentStatusMapper paymentStatusMapper;

	@Autowired
	private OrderStatusMapper orderStatusMapper;

	@GetMapping("/myOrder")
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

	@PostMapping(value = "/myOrder")
	public String submitOrder(@Valid @ModelAttribute("newOrder") OrderDTO orderDTO, BindingResult result,
			HttpServletRequest request, Authentication authentication, Model model)
			throws IOException, TimeoutException {
		String cartToOrder;
		String username = authentication.getName();
		CartDTO cartDTO = cartService.getById(Integer.parseInt(WebUtils.getCookie(request, "cartId").getValue()));
		
		try {
			cartToOrder = orderService.cartToOrder(orderDTO, cartDTO, username);
		}
		catch (CustomLogicException ex) {
			model.addAttribute("error", "Empty customer profile!");
			LOGGER.info(username + " failed to create new order");
			return "/myOrder";
		}

		if (PaymentTypeDTO.CREDIT_CART.equals(orderDTO.getPaymentType())) {
			request.getSession().setAttribute("totalPrice", orderDTO.getOrderPrice());
			return "redirect:/myOrder/cardPayment";
		}

		if (!cartToOrder.equals("success")) {
			model.addAttribute("error", cartToOrder);
			LOGGER.info(username + " failed to create new order");
			return "/myOrder";
		}

		return "redirect:/orderHistory";

	}

	@GetMapping(value = "/admin/orderStatus")
	public String showOrderStatus(Model model, HttpServletRequest request) {
		model.addAttribute("orders", orderService.getAll());
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}

	@GetMapping(value = "/orderHistory")
	public String showOrdersForUser(Model model, HttpServletRequest request, Authentication authentication) {
		String username = authentication.getName();
		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		model.addAttribute("orders", orderService.getByCustomerId(customerDTO.getCustomerId()));
		model.addAttribute("updatedOrder", new OrderDTO());
		return "orderList";
	}

	@PostMapping(value = "/admin/orderStatus",  produces="application/json")
	public ResponseEntity<?> changeOrderStatus(@RequestParam("orderId") int orderId,
			@RequestParam("orderStatus") OrderStatusDTO orderStatus,
			@RequestParam("paymentStatus") PaymentStatusDTO paymentStatus, Model model, Authentication authentication,
			HttpServletRequest request) throws IOException, TimeoutException {

		String result = orderService.changeOrderStatus(orderId, orderStatusMapper.backward(orderStatus),
													   paymentStatusMapper.backward(paymentStatus));
		
		if ("success".equals(result)) {
			LOGGER.error("Modifications after order completion");
			return  ResponseEntity.badRequest().body("No update after order completion");
		}

		LOGGER.info("Admin has updated the order");
		
		List<OrderDTO> orders = orderService.getAll();

		return ResponseEntity.ok(orders);
	}

	@GetMapping(value = "/myOrder/cardPayment")
	public String showCardPaymentPage(Authentication authentication, Model model,
			HttpServletRequest request) {
		model.addAttribute("total", request.getSession().getAttribute("totalPrice"));
		return "cardPayment";
	}

	@PostMapping(value = "/myOrder/cardPayment")
	public String processCardPayment(HttpServletRequest request, Model model)  {
		return "redirect:/orderHistory";
	}

}
