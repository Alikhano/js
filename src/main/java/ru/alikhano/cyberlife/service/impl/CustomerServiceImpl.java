package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.mapper.CustomerMapper;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerMapper customerMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<CustomerDTO> getAll() {
		List<CustomerDTO> customersDTO = new ArrayList<>();
		for (Customer customer : customerDao.getAll()) {
			CustomerDTO customerDTO = customerMapper.forward(customer);
			customersDTO.add(customerDTO);
		}
		return customersDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CustomerDTO getById(int id) {
		return customerMapper.forward(customerDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(CustomerDTO customerDTO) {
		if (customerDTO == null) {
			LOGGER.info("Customer is null");
			return;
		}
		customerDao.create(customerMapper.backward(customerDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(CustomerDTO customerDTO){
		if (customerDTO == null) {
			LOGGER.info("Customer is null");
			return;
		}
		Customer customer = customerDao.getById(customerDTO.getCustomerId());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		customer.setBirthDate(customerDTO.getBirthDate());
		customer.setEmail(customerDTO.getEmail());
		customerDao.update(customer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(CustomerDTO customerDTO) {
		if (customerDTO == null) {
			LOGGER.info("Customer is null");
			return;
		}
		customerDao.delete(customerMapper.backward(customerDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CustomerDTO getByUserId(int userId) {
		return customerMapper.forward(customerDao.getByUserId(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<CustomerDTO> getTopCustomers() {
		return customerDao.getTopCustomers().stream()
				.filter(Objects::nonNull)
				.map(customer -> customerMapper.forward(customer))
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional 
	public CustomerDTO getByEmail(String email) {
		if (StringUtils.isNullOrEmpty(email)) {
			LOGGER.info("Email is null or empty");
			return null;
		}
		return customerMapper.forward(customerDao.getByEmail(email));
	}
}
