package ru.alikhano.cyberlife.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.dao.OrderItemDao;
import ru.alikhano.cyberlife.mapper.OrderItemMapper;

import ru.alikhano.cyberlife.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	private static final Logger LOGGER = LogManager.getLogger(OrderItemServiceImpl.class);
	
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
		if (orderItemDTO == null) {
			LOGGER.info("Order item is null");
			return;
		}
		orderItemDao.create(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(OrderItemDTO orderItemDTO) {
		if (orderItemDTO == null) {
			LOGGER.info("Order item is null");
			return;
		}
		orderItemDao.update(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(OrderItemDTO orderItemDTO) {
		if (orderItemDTO == null) {
			LOGGER.info("Order item is null");
			return;
		}
		orderItemDao.delete(orderItemMapper.backward(orderItemDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteAll(OrderDTO orderDTO) {
		if (orderDTO == null || CollectionUtils.isEmpty(orderDTO.getOrderItems())) {
			LOGGER.info("Order and/or order items are null");
			return;
		}
		for (OrderItemDTO item : orderDTO.getOrderItems()) {
			delete(item);
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
