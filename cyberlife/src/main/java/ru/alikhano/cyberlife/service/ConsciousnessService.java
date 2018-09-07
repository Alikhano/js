package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.ConsDTO;

public interface ConsciousnessService {
	
	List<ConsDTO> getConsDTOList();

	ConsDTO getConsDTOById(int id);

	ConsDTO getConsDTOByLevel(String consLevel);

	void addLevel(ConsDTO consDTO);

}
