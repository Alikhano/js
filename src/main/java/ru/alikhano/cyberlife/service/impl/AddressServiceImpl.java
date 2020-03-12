package ru.alikhano.cyberlife.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.AddressDTO;
import ru.alikhano.cyberlife.dao.AddressDao;
import ru.alikhano.cyberlife.mapper.AddressMapper;

import ru.alikhano.cyberlife.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private static final Logger LOGGER = LogManager.getLogger(AddressServiceImpl.class);

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void create(AddressDTO addressDTO) {
    	if (addressDTO == null) {
    		LOGGER.info("Address is null");
    		return;
		}
        addressDao.create(addressMapper.backward(addressDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(AddressDTO addressDTO) {
		if (addressDTO == null) {
			LOGGER.info("Address is null");
			return;
		}
        addressDao.update(addressMapper.backward(addressDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(AddressDTO addressDTO) {
		if (addressDTO == null) {
			LOGGER.info("Address is null");
			return;
		}
        addressDao.delete(addressMapper.backward(addressDTO));
    }

}
