package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.service.impl.CustomerServiceImpl;
import ru.alikhano.cyberlife.supplier.CustomerSupplier;

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

	private static final String TEST_EMAIL = "johnsanna@gmail.com";
	
	@Before
	public void init() {
	   customer = CustomerSupplier.getCustomer();
	   customerDTO = CustomerSupplier.getCustomerDTO();
	   List<Customer> customers = CustomerSupplier.getCustomers();
	   customersDTO = CustomerSupplier.getCustomersDTO();
	   
	   Mockito.when(customerDao.getById(1)).thenReturn(customer);
	   Mockito.when(customerDao.getByEmail(TEST_EMAIL)).thenReturn(customer);
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
		CustomerDTO customer = customerService.getByEmail(TEST_EMAIL);
		assertEquals(1, customer.getCustomerId());
		Mockito.verify(customerDao).getByEmail(TEST_EMAIL);
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
