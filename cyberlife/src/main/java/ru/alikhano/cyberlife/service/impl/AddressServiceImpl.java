package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	AddressMapper addressMapper;

	@Override
	@Transactional
	public List<AddressDTO> getAll() {
		List<AddressDTO> addressesDTO = new ArrayList<>();
		List<Address> addresses = addressDao.getAll();
		for (Address address : addresses) {
			AddressDTO addressDTO = addressMapper.addressToAddressDTO(address);
			addressesDTO.add(addressDTO);
		}
		return addressesDTO;
	}

	@Override
	@Transactional
	public AddressDTO getById(int id) {
		return addressMapper.addressToAddressDTO(addressDao.getById(id));
	}

	@Override
	@Transactional
	public void create(AddressDTO addressDTO) {
		addressDao.create(addressMapper.addressDTOtoAddress(addressDTO));
		
	}

	@Override
	@Transactional
	public void update(AddressDTO addressDTO) {
		addressDao.update(addressMapper.addressDTOtoAddress(addressDTO));
		
	}

	@Override
	@Transactional
	public void delete(AddressDTO addressDTO) {
		addressDao.delete(addressMapper.addressDTOtoAddress(addressDTO));
		
	}

}
