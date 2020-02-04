package ru.alikhano.cyberlife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dao.OrderItemDao;
import ru.alikhano.cyberlife.mapper.OrderItemMapper;

import ru.alikhano.cyberlife.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private OrderItemMapper orderItemMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(OrderItemDTO orderItemDTO) {
		orderItemDao.create(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(OrderItemDTO orderItemDTO) {
		orderItemDao.update(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(OrderItemDTO orderItemDTO) {
		orderItemDao.delete(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteAll(OrderDTO orderDTO) {
		for (OrderItemDTO item : orderDTO.getOrderedItems()) {
			orderItemDao.delete(orderItemMapper.backward(item));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public OrderItemDTO getById(int id) {
		return orderItemMapper.forward(orderItemDao.getById(id));
	}
}
