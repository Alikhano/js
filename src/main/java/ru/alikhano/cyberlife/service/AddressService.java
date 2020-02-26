package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.dto.AddressDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Service
public interface AddressService {
	
	/**
	 * @return list of all addresses in the database
	 */
	List<AddressDTO> getAll();

	/**
	 * searches for address by customer's id
	 * @param id customer's id 
	 * @return instance of AddressDTO for a corresponding customer
	 */
	AddressDTO getById(int id);

	/**
	 * @param addressDTO instance of AddressDTO to convert to Address and add to the database
	 */
	void create(AddressDTO addressDTO);
	
	/**
	 * @param addressDTO instance of updated AddressDTO to convert to Address and add to the database
	 */
	void update(AddressDTO addressDTO);

	/**
	 * @param addressDTO instance of AddressDTO to convert to Address and delete from the database
	 */
	void delete(AddressDTO addressDTO);

}
