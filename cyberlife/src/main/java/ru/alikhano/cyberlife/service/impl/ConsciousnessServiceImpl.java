package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.ConsDTO;
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
	public List<ConsDTO> getConsDTOList() {
		List<Consciousness> consList = consDao.getConList();
		List<ConsDTO> consesDTO = new ArrayList<>();
		consList.stream().forEach(cons -> {
			ConsDTO consDTO = consMapper.consToConsDTO(cons);
			consesDTO.add(consDTO);
		});
		return consesDTO;
	}

	@Override
	public ConsDTO getConsDTOById(int id) {
		return consMapper.consToConsDTO(consDao.getConsById(id));
	}

	@Override
	public ConsDTO getConsDTOByLevel(String consLevel) {
		return consMapper.consToConsDTO(consDao.getConsByLevel(consLevel));
	}

	@Override
	public void addLevel(ConsDTO consDTO) {
		consDao.addLevel(consMapper.consDTOtoCons(consDTO));
		
	}

}
