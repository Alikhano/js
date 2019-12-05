package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.mapper.CustomerMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	@Mock
	private CustomerDao customerDao;
	@Mock
	private CustomerMapper customerMapper;
	
	@InjectMocks
	private CustomerServiceImpl customerService;

	private Customer customer;
	private CustomerDTO customerDTO;
	private List<CustomerDTO> customersDTO;
	
	@Before
	public void init() {
	   Address address = new Address(1, "UK", "London", "198303","Sptring street","10","5");
	   customer = new Customer(1, "Anna", "Johnson", "1994-10-26", "johnsanna@gmail.com", address);
	   customerDTO = new CustomerDTO(customer);
	   List<Customer> customers = new ArrayList<>();
	   customersDTO = new ArrayList<>();
	   customers.add(customer);
	   customersDTO.add(customerDTO);
	   
	   Mockito.when(customerDao.getById(1)).thenReturn(customer);
	   Mockito.when(customerDao.getByEmail("johnsanna@gmail.com")).thenReturn(customer);
	   Mockito.when(customerDao.getAll()).thenReturn(customers);
	   Mockito.when(customerMapper.customerDTOtoCustomer(customerDTO)).thenReturn(customer);
	   Mockito.when(customerMapper.customerToCustomerDTO(customer)).thenReturn(customerDTO);
	   Mockito.doNothing().when(customerDao).create(customer);
	   Mockito.doNothing().when(customerDao).update(customer);
	   Mockito.doNothing().when(customerDao).delete(customer);
	   Mockito.when(customerDao.getTopCustomers()).thenReturn(customers);
	   Mockito.when(customerDao.getByUserId(1)).thenReturn(customer);
	}
	
	@Test
	public void create() {
		customerService.create(customerDTO);
		Mockito.verify(customerDao).create(customer);
	}
	
	@Test
	public void delete() {
		customerService.delete(customerDTO);
		Mockito.verify(customerDao).delete(customer);
	}
	
	@Test
	public void update() {
		customerService.update(customerDTO);
		Mockito.verify(customerDao).update(customer);
		
	}
	
	@Test
	public void getById() {
		CustomerDTO customer = customerService.getById(1);
		assertEquals(1, customer.getCustomerId());
		Mockito.verify(customerDao).getById(1);
	}
	
	@Test
	public void getByIdFail() {
		CustomerDTO customer = customerService.getById(2);
		assertNull(customer);
	}
	
	@Test
	public void getByEmail() {
		CustomerDTO customer = customerService.getByEmail("johnsanna@gmail.com");
		assertEquals(1, customer.getCustomerId());
		Mockito.verify(customerDao).getByEmail("johnsanna@gmail.com");
	}
	
	@Test
	public void getByEmailFail() {
		CustomerDTO customer = customerService.getByEmail("johnsanna@yandex.com");
		assertNull(customer);
	}
	
	@Test
	public void getAll() {
		List<CustomerDTO> list = customerService.getAll();
		assertEquals(customersDTO, list);
		Mockito.verify(customerDao).getAll();
	}
	
	@Test
	public void getTopCustomer() {
		List<CustomerDTO> list = customerService.getTopCustomers();
		assertEquals(list, customersDTO);
		Mockito.verify(customerDao).getTopCustomers();
	}
	
	@Test
	public void getByUserId() {
		CustomerDTO customer = customerService.getByUserId(1);
		assertEquals(customer, customerDTO);
		Mockito.verify(customerDao).getByUserId(1);
	}
	
	@Test
	public void getByUserIdFail() {
		CustomerDTO customer = customerService.getByUserId(2);
		assertNull(customer);
	}
}
