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

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;
import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.DTO.OrderItemDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.model.Orders;
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
	OrderDao orderDao;

	@Autowired
	UserService userService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CartService cartService;

	@Autowired
	CartItemService cartItemService;
	
	@Autowired
	OrderItemService orderItemService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	MessagingService messaginService;

	@Autowired
	OrderMapper orderMapper;
	

	@Override
	@Transactional
	public void create(OrderDTO orderDTO) {
		orderDao.create(orderMapper.orderDTOtoOder(orderDTO));
	}

	@Override
	@Transactional
	public List<OrderDTO> getAll() {
		List<Orders> orders = orderDao.getAll();
		List<OrderDTO> ordersDTO = new ArrayList<>();
		orders.stream().forEach(order -> {
			OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
			ordersDTO.add(orderDTO);
		});
		return ordersDTO;
	}

	@Override
	@Transactional
	public OrderDTO getById(int id) {
		return orderMapper.orderToOrderDTO(orderDao.getById(id));
	}

	@Override
	@Transactional
	public void update(OrderDTO orderDTO) throws IOException, TimeoutException {
		orderDao.update(orderMapper.orderDTOtoOder(orderDTO));
		if (orderDTO.getPaymentStatus().equals("paid")) {
			if (isInTop(orderDTO)) {
				messaginService.sendUpdateMessage("table should be updated!");
			}
		}

	}

	@Override
	@Transactional
	public List<OrderDTO> getByCustomerId(int id) {
		List<Orders> orders = orderDao.getOrdersByCustomerId(id);
		List<OrderDTO> ordersDTO = new ArrayList<>();
		orders.stream().forEach(order -> {
			OrderDTO orderDTO = orderMapper.orderToOrderDTO(order);
			ordersDTO.add(orderDTO);
		});
		return ordersDTO;
	}

	@Override
	@Transactional
	public int createAndGetId(OrderDTO order) {
		return orderDao.createAndGetId(orderMapper.orderDTOtoOder(order));
	}

	@Override
	@Transactional
	public Map<Integer, Double> getMonthlyRevenue() {
		return orderDao.getMonthlyRevenue();
	}

	@Override
	@Transactional
	public double getWeeklyRevenue() {
		return orderDao.getWeeklyRevenue();
	}

	@Override
	@Transactional
	public String cartToOrder(OrderDTO orderDTO, CartDTO cartDTO, String username) throws CustomLogicException, IOException, TimeoutException {

		UserDTO user = userService.getByUsernameDTO(username);
		CustomerDTO customerDTO = customerService.getByUserId(user.getUserId());
		
		if (customerDTO.getAddress() == null || customerDTO.getEmail() == null || customerDTO.getLastName() == null) {
			throw new CustomLogicException("Your customer profile must miss some information. Please go back to your profile and complete it.");
		}

		// assign customer to order
		orderDTO.setCustomer(customerDTO);
	
		
		//set price to order, erase price from cart
		orderDTO.setOrderPrice(cartDTO.getGrandTotal());
		cartDTO.setGrandTotal(0);
		//add date to order
		orderDTO.setOrderDate(new Date());
		
     	//update order status
		
		if (orderDTO.getPaymentType().equals("credit card")) {
			orderDTO.setPaymentStatus("paid");
		}
		else {
			orderDTO.setPaymentStatus("unpaid");
		}
			
		orderDTO.setOrderStatus("awaits delivery");
		
		//set ordered items, erase cart items
		Set<CartItemDTO> items = cartDTO.getItems();
		
		int orderId = createAndGetId(orderDTO);

		for (CartItemDTO cartItem : items) {
			//decrease units in stock for product, update product
			ProductDTO productDTO = productService.selectForUpdate(cartItem.getProduct().getProductId());
			int prevQuantity = productDTO.getUnitsInStock();
			if (prevQuantity == 0) {
				
			throw new CustomLogicException("You tried to submit order while one of the items is out of stock. Please delete item from cart and proceed to order");
			}
			else if (prevQuantity < cartItem.getQuantity()) {
				throw new CustomLogicException("You tried to submit order while one of the items has less items in stock than you need. Please delete item from cart and proceed to order");
			}
			else {
				productDTO.setUnitsInStock(prevQuantity - cartItem.getQuantity());
				productService.merge(productDTO);
							
			}
			
			//cartItem into orderItem
			OrderItemDTO item = new OrderItemDTO();
			item.setOrderQuantity(cartItem.getQuantity());
			item.setOrderTotal(cartItem.getTotalPrice());
			item.setProduct(cartItem.getProduct());
			getById(orderId).addItem(item);
			orderItemService.create(item);
			
		}
		
		cartItemService.deleteAll(cartDTO);
		items.clear();
	
		cartDTO.setItems(items);
		
		//update cart, create order
		cartService.update(cartDTO);
		
		return "success";

	}

	@Override
	@Transactional
	public boolean isInTop(OrderDTO order) {
		Set<OrderItemDTO> orderItems = order.getOrderedItems();
		List<ProductDTO> top = productService.getTopProducts();
		for (OrderItemDTO orderItem : orderItems) {
			ProductDTO productDTO = orderItem.getProduct();	
			for (ProductDTO product : top) {
				if (product.getProductId() == productDTO.getProductId()) {
					return true;
				}
			}
		}
		return false;
	}

}
