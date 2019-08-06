package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.dto.ConsDTO;


/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface ConsciousnessService {
	
	/**
	 * @return list of all AI configurations
	 */
	List<ConsDTO> getAll();

	/**
	 * searches for a specific AI configuration by id
	 * @param id of a configuration that user's searching for
	 * @return instance of ConsDTO
	 */
	ConsDTO getById(int id);

	/**
	 * searches for a specific AI configuration by name
	 * @param consLevel name of a configuration that user's searching for
	 * @return instance of ConsDTO
	 */
	ConsDTO getByLevel(String consLevel);

	/**
	 * creates new AI configuration
	 * @param consDTO
	 */
	void create(ConsDTO consDTO);

}
