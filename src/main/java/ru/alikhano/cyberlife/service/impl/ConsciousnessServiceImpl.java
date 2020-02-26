package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.dao.ConsciousnessDao;
import ru.alikhano.cyberlife.mapper.ConsciousnessMapper;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Service
public class ConsciousnessServiceImpl implements ConsciousnessService{
	
	@Autowired
	private ConsciousnessDao consciousnessDao;
	
	@Autowired
	private ConsciousnessMapper consciousnessMapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<ConsciousnessDTO> getAll() {
		List<ConsciousnessDTO> consesDTO = new ArrayList<>();
		consciousnessDao.getAll().forEach(cons -> {
			ConsciousnessDTO consciousnessDTO = consciousnessMapper.forward(cons);
			consesDTO.add(consciousnessDTO);
		});
		return consesDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsciousnessDTO getById(int id) {

		return consciousnessMapper.forward(consciousnessDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsciousnessDTO getByLevel(String consciousnessLevel) {
		return consciousnessMapper.forward(consciousnessDao.getConsByLevel(consciousnessLevel));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(ConsciousnessDTO consciousnessDTO) {
		consciousnessDao.create(consciousnessMapper.backward(consciousnessDTO));
	}
}
