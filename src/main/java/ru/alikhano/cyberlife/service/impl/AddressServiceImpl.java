package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;
import ru.alikhano.cyberlife.model.Address;
import ru.alikhano.cyberlife.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private AddressMapper addressMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<AddressDTO> getAll() {
		List<AddressDTO> addressesDTO = new ArrayList<>();

		for (Address address : addressDao.getAll()) {
			AddressDTO addressDTO = addressMapper.forward(address);
			addressesDTO.add(addressDTO);
		}

		return addressesDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public AddressDTO getById(int id) {

		return addressMapper.forward(addressDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(AddressDTO addressDTO) {

		addressDao.create(addressMapper.backward(addressDTO));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void update(AddressDTO addressDTO) {

		addressDao.update(addressMapper.backward(addressDTO));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(AddressDTO addressDTO) {

		addressDao.delete(addressMapper.backward(addressDTO));
	}

}
