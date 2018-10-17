package ru.alikhano.cyberlife.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.service.impl.AddressServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceTest {
	
	@Mock
	AddressDao addressDao;
	
	@InjectMocks
	AddressServiceImpl addressService;
	
	@Mock
	AddressMapper addressMapper;
	
	Address addressMock;
	AddressDTO addressDTOMock;
	List<Address> addresses;
	List<AddressDTO> addressesDTO;
	
	@Before
	public void init() {
		addressMock = new Address(1, "UK", "London", "198303","Sptring street","10","5");
		addressDTOMock = new AddressDTO(addressMock);
		addresses = new ArrayList<>();
		addressesDTO = new ArrayList<>();
		addresses.add(addressMock);
		addressesDTO.add(addressDTOMock);
		
		Mockito.when(addressDao.getById(1)).thenReturn(addressMock);
		Mockito.when(addressDao.getAll()).thenReturn(addresses);
		Mockito.when(addressMapper.addressDTOtoAddress(addressDTOMock)).thenReturn(addressMock);
		Mockito.when(addressMapper.addressToAddressDTO(addressMock)).thenReturn(addressDTOMock);
		
		Mockito.doNothing().when(addressDao).create(addressMock);
		
		Mockito.doNothing().when(addressDao).update(addressMock);
		
		Mockito.doNothing().when(addressDao).delete(addressMock);
		
	}
	
	@Test
	public void create() {
		addressService.create(addressDTOMock);
		addressDao.create(addressMock);
	}
	
	@Test
	public void update() {
		addressService.update(addressDTOMock);
		addressDao.update(addressMock);
	}
	
	@Test
	public void delete() {
		addressService.delete(addressDTOMock);
		addressDao.delete(addressMock);
	}
	
	@Test
	public void getAll() {
		List<AddressDTO> list = addressService.getAll();
		assertEquals(addressesDTO, list);
	}
	
	@Test
	public void getById() {
		AddressDTO address = addressService.getById(1);
		assertEquals(1, address.getAddressId());
	}

}
