package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.ConsDTO;
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
	public List<ConsDTO> getAll() {
		List<ConsDTO> consesDTO = new ArrayList<>();
		consciousnessDao.getAll().forEach(cons -> {
			ConsDTO consDTO = consciousnessMapper.consToConsDTO(cons);
			consesDTO.add(consDTO);
		});
		return consesDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsDTO getById(int id) {

		return consciousnessMapper.consToConsDTO(consciousnessDao.getById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public ConsDTO getByLevel(String consciousnessLevel) {
		return consciousnessMapper.consToConsDTO(consciousnessDao.getConsByLevel(consciousnessLevel));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void create(ConsDTO consDTO) {
		consciousnessDao.create(consciousnessMapper.consDTOtoCons(consDTO));
	}
}
