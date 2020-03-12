package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.service.impl.AddressServiceImpl;
import ru.alikhano.cyberlife.supplier.AddressSupplier;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {
	
	@Mock
	private AddressDao addressDao;
	@Mock
	private AddressMapper addressMapper;
	
	@InjectMocks
	private AddressServiceImpl addressService;

	private Address address;
	private AddressDTO addressDTO;
	
	@Before
	public void init() {
		address = AddressSupplier.getAddress();
		addressDTO = AddressSupplier.getAddressDTO();

		Mockito.when(addressMapper.backward(addressDTO)).thenReturn(address);
		
		Mockito.doNothing().when(addressDao).create(address);
		Mockito.doNothing().when(addressDao).update(address);
		Mockito.doNothing().when(addressDao).delete(address);
		
	}
	
	@Test
	public void create() {
		addressService.create(addressDTO);
		Mockito.verify(addressDao).create(address);
	}
	
	@Test
	public void update() {
		addressService.update(addressDTO);
		Mockito.verify(addressDao).update(address);
	}
	
	@Test
	public void delete() {
		addressService.delete(addressDTO);
		Mockito.verify(addressDao).delete(address);
	}

}
