package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.dao.OrderDao;
import ru.alikhano.cyberlife.mapper.OrderMapper;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;
	
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
	public void update(OrderDTO orderDTO) {
		orderDao.update(orderMapper.orderDTOtoOder(orderDTO));
		
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

}
