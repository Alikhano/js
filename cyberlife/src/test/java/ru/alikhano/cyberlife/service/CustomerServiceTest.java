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

import ru.alikhano.cyberlife.dto.CustomLogicException;
import ru.alikhano.cyberlife.dto.CustomerDTO;
import ru.alikhano.cyberlife.dao.CustomerDao;
import ru.alikhano.cyberlife.mapper.CustomerMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.model.Customer;
import ru.alikhano.cyberlife.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
	
	@Mock
	CustomerDao customerDao;
	
	@Mock 
	CustomerMapper customerMapper;
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	Customer customerMock;
	CustomerDTO customerDTOMock;
	List<Customer> customers;
	List<CustomerDTO> customersDTO;
	
	@Before
	public void init() {
	   Address address = new Address(1, "UK", "London", "198303","Sptring street","10","5");
	   customerMock = new Customer(1, "Anna", "Johnson", "1994-10-26", "johnsanna@gmail.com", address);
	   customerDTOMock = new CustomerDTO(customerMock);
	   customers = new ArrayList<>();
	   customersDTO = new ArrayList<>();
	   customers.add(customerMock);
	   customersDTO.add(customerDTOMock);
	   
	   Mockito.when(customerDao.getById(1)).thenReturn(customerMock);
	   Mockito.when(customerDao.getByEmail("johnsanna@gmail.com")).thenReturn(customerMock);
	   Mockito.when(customerDao.getAll()).thenReturn(customers);
	   Mockito.when(customerMapper.customerDTOtoCustomer(customerDTOMock)).thenReturn(customerMock);
	   Mockito.when(customerMapper.customerToCustomerDTO(customerMock)).thenReturn(customerDTOMock);
	   
	   Mockito.doNothing().when(customerDao).create(customerMock);
		
		Mockito.doNothing().when(customerDao).update(customerMock);
		
		Mockito.doNothing().when(customerDao).delete(customerMock);
		
		Mockito.when(customerDao.getTopCustomers()).thenReturn(customers);
		Mockito.when(customerDao.getByUserId(1)).thenReturn(customerMock);
	
	}
	
	@Test
	public void create() {
		customerService.create(customerDTOMock);
		Mockito.verify(customerDao).create(customerMock);
	}
	
	@Test
	public void delete() {
		customerService.delete(customerDTOMock);
		Mockito.verify(customerDao).delete(customerMock);
	}
	
	@Test
	public void update() throws CustomLogicException {
		customerService.update(customerDTOMock);
		Mockito.verify(customerDao).update(customerMock);
		
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
		assertEquals(customer, customerDTOMock);
		Mockito.verify(customerDao).getByUserId(1);
	}
	
	@Test
	public void getByUserIdFail() {
		CustomerDTO customer = customerService.getByUserId(2);
		assertNull(customer);
	}
	

}
