package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.ConsDTO;

public interface ConsciousnessService {
	
	List<ConsDTO> getAll();

	ConsDTO getById(int id);

	ConsDTO getByLevel(String consLevel);

	void create(ConsDTO consDTO);

}
