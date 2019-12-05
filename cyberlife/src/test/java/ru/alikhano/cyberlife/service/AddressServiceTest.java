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

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.service.impl.AddressServiceImpl;

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
	private List<AddressDTO> addressesDTO;
	
	@Before
	public void init() {
		address = new Address(1, "UK", "London", "198303",
							  "Spring street", "10", "5");
		addressDTO = new AddressDTO(address);
		List<Address> addresses = new ArrayList<>();
		addressesDTO = new ArrayList<>();
		addresses.add(address);
		addressesDTO.add(addressDTO);
		
		Mockito.when(addressDao.getById(1)).thenReturn(address);
		Mockito.when(addressDao.getAll()).thenReturn(addresses);
		Mockito.when(addressMapper.addressDTOtoAddress(addressDTO)).thenReturn(address);
		Mockito.when(addressMapper.addressToAddressDTO(address)).thenReturn(addressDTO);
		
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
	
	@Test
	public void getByIdFail() {
		AddressDTO address = addressService.getById(2);
		assertNull(address);
	}

}
