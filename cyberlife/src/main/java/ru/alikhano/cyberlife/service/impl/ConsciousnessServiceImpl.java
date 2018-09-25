package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.dao.ConsciousnessDao;
import ru.alikhano.cyberlife.mapper.ConsciousnessMapper;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.service.ConsciousnessService;

@Service
public class ConsciousnessServiceImpl implements ConsciousnessService{
	
	@Autowired
	ConsciousnessDao consDao;
	
	@Autowired
	ConsciousnessMapper consMapper;

	@Override
	@Transactional
	public List<ConsDTO> getAll() {
		List<Consciousness> consList = consDao.getAll();
		List<ConsDTO> consesDTO = new ArrayList<>();
		consList.stream().forEach(cons -> {
			ConsDTO consDTO = consMapper.consToConsDTO(cons);
			consesDTO.add(consDTO);
		});
		return consesDTO;
	}

	@Override
	@Transactional
	public ConsDTO getById(int id) {
		return consMapper.consToConsDTO(consDao.getById(id));
	}

	@Override
	@Transactional
	public ConsDTO getByLevel(String consLevel) {
		return consMapper.consToConsDTO(consDao.getConsByLevel(consLevel));
	}

	@Override
	@Transactional
	public void create(ConsDTO consDTO) {
		consDao.create(consMapper.consDTOtoCons(consDTO));
		
	}

}
