package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.mapper.CustomerMapper;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

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
		List<Customer> customers = customerDao.getAll();
		for (Customer customer : customers) {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
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
		return customerMapper.customerToCustomerDTO(customerDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(CustomerDTO customerDTO) {
		customerDao.create(customerMapper.customerDTOtoCustomer(customerDTO));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(CustomerDTO customerDTO) throws CustomLogicException {
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
		customerDao.delete(customerMapper.customerDTOtoCustomer(customerDTO));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public CustomerDTO getByUserId(int userId) {
		return customerMapper.customerToCustomerDTO(customerDao.getByUserId(userId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<CustomerDTO> getTopCustomers() {
		List<Customer> customerList = customerDao.getTopCustomers();
		List<CustomerDTO> dtoList = new ArrayList<>();

		for (Customer customer : customerList) {
			CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
			dtoList.add(customerDTO);
		}

		return dtoList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional 
	public CustomerDTO getByEmail(String email) {
		return customerMapper.customerToCustomerDTO(customerDao.getByEmail(email));
	}
}
