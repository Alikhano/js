package ru.alikhano.cyberlife.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dto.UserDTO;
import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.enums.OrderStatus;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;
import ru.alikhano.cyberlife.service.CustomerService;
import ru.alikhano.cyberlife.service.OrderItemService;
import ru.alikhano.cyberlife.service.OrderService;
import ru.alikhano.cyberlife.service.ProductService;
import ru.alikhano.cyberlife.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MessagingService messaginService;

	@Autowired
	private OrderMapper orderMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(OrderDTO orderDTO) {
		orderDao.create(orderMapper.backward(orderDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<OrderDTO> getAll() {
		List<Orders> orders = orderDao.getAll();
		List<OrderDTO> ordersDTO = new ArrayList<>();
		orders.forEach(order -> {
			OrderDTO orderDTO = orderMapper.forward(order);
			ordersDTO.add(orderDTO);
		});
		return ordersDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public OrderDTO getById(int id) {
		return orderMapper.forward(orderDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(OrderDTO orderDTO) throws IOException, TimeoutException {
		orderDao.update(orderMapper.backward(orderDTO));
		if (PaymentStatusDTO.PAID.equals(orderDTO.getPaymentStatus()) && isInTop(orderDTO)) {
				messaginService.sendUpdateMessage("table should be updated!");			
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<OrderDTO> getByCustomerId(int id) {
		List<Orders> orders = orderDao.getOrdersByCustomerId(id);
		List<OrderDTO> ordersDTO = new ArrayList<>();
		orders.forEach(order -> {
			OrderDTO orderDTO = orderMapper.forward(order);
			ordersDTO.add(orderDTO);
		});
		return ordersDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public int createAndGetId(OrderDTO order) {
		return orderDao.createAndGetId(orderMapper.backward(order));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Map<Integer, Double> getMonthlyRevenue() {
		return orderDao.getMonthlyRevenue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public double getWeeklyRevenue() {
		return orderDao.getWeeklyRevenue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public String cartToOrder(OrderDTO orderDTO, CartDTO cartDTO, String username) throws CustomLogicException,
			IOException, TimeoutException {

		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		
		if (customerDTO.getAddress() == null || customerDTO.getEmail() == null || customerDTO.getLastName() == null) {
			throw new CustomLogicException("Your customer profile must miss some information. " +
												   "Please go back to your profile and complete it.");
		}

		OrderDTO initiatedOrder = initiateOrder(orderDTO, customerDTO, cartDTO);

		int orderId = createAndGetId(initiatedOrder);
		
		moveItemsFromCartToOrder(orderId, cartDTO);
		
		return "success";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public boolean isInTop(OrderDTO order) {
		Set<OrderItemDTO> orderItems = order.getOrderItems();
		List<ProductDTO> top = productService.getTopProducts();
		for (OrderItemDTO orderItem : orderItems) {
			ProductDTO productDTO = orderItem.getProduct();	
			for (ProductDTO productFromTop : top) {
				if (productFromTop.getProductId().equals(productDTO.getProductId())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public String changeOrderStatus(int orderId, OrderStatus orderStatus, PaymentStatus paymentStatus) {
		Orders order = orderDao.getById(orderId);
		if (PaymentStatus.PAID.equals(order.getPaymentStatus()) && PaymentStatus.UNPAID.equals(paymentStatus)) {
			return "No payment status change - already paid";
		}
		if (OrderStatus.RECEIVED.equals(order.getOrderStatus())) {
			return "No status updates after order completion!";
		}

		order.setOrderStatus(orderStatus);
		order.setPaymentStatus(paymentStatus);
		orderDao.merge(order);
		
		return "success";
	}

	private OrderDTO initiateOrder(OrderDTO orderDTO, CustomerDTO customerDTO, CartDTO cartDTO) {
		// assign customer to order
		orderDTO.setCustomer(customerDTO);

		//set price to order, erase price from cart
		orderDTO.setOrderPrice(cartDTO.getGrandTotal());
		cartDTO.setGrandTotal(0.0);

		//add date to order
		orderDTO.setOrderDate(new Date());

		//update order status

		if (PaymentTypeDTO.CREDIT_CART.equals(orderDTO.getPaymentType())) {
			orderDTO.setPaymentStatus(PaymentStatusDTO.PAID);
		}
		else {
			orderDTO.setPaymentStatus(PaymentStatusDTO.UNPAID);
		}

		orderDTO.setOrderStatus(OrderStatusDTO.AWAITS_DELIVERY);

		return orderDTO;
	}

	private void moveItemsFromCartToOrder(Integer orderId, CartDTO cartDTO)
			throws CustomLogicException, IOException, TimeoutException {

		Set<CartItemDTO> items = cartDTO.getItems();

		for (CartItemDTO cartItem : items) {
			//decrease units in stock for product, update product
			ProductDTO productDTO = productService.selectForUpdate(cartItem.getProduct().getProductId());
			int prevQuantity = productDTO.getUnitsInStock();
			if (prevQuantity == 0) {

				throw new CustomLogicException("You tried to submit order while one of the items is out of stock. " +
													   "Please delete item from cart and proceed to order");
			}
			else if (prevQuantity < cartItem.getQuantity()) {
				throw new CustomLogicException("You tried to submit order while one of the items has less items in " +
													   "stock than you need. Please delete item from cart and proceed to order");
			}
			else {
				productDTO.setUnitsInStock(prevQuantity - cartItem.getQuantity());
				productService.merge(productDTO);
			}
			createCartItemFromOrderItem(cartItem, orderId);
		}
		cartItemService.deleteAll(cartDTO);
		items.clear();

		cartDTO.setItems(items);

		//update cart, create order
		cartService.update(cartDTO);
	}

	private void createCartItemFromOrderItem(CartItemDTO cartItemDTO, Integer orderId) {
		OrderItemDTO item = new OrderItemDTO();
		item.setOrderQuantity(cartItemDTO.getQuantity());
		item.setOrderTotal(cartItemDTO.getTotalPrice());
		item.setProduct(cartItemDTO.getProduct());
		getById(orderId).addItem(item);
		orderItemService.create(item);
	}
}
