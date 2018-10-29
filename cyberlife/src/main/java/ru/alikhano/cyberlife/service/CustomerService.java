package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Service
public interface CustomerService {
	
	/**
	 * @return list of all customers
	 */
	List<CustomerDTO> getAll();

	/**
	 * searches for a specific customer by id
	 * @param id of a customer that we're searching for
	 * @return instance of CustomerDTO
	 */
	CustomerDTO getById(int id);

	/**
	 * creates new customer entry
	 * @param customerDTO
	 */
	void create(CustomerDTO customerDTO);

	/**
	 * updates and existing customer entry
	 * @param customerDTO
	 * @throws CustomLogicException
	 */
	void update(CustomerDTO customerDTO) throws CustomLogicException;

	/**
	 * deletes a customer entry
	 * @param customerDTO
	 */
	void delete(CustomerDTO customerDTO);
	
	/**
	 * searches for a specific customer by user id
	 * @param userId of a customer that we're searching for
	 * @return instance of CustomerDTO
	 */
	CustomerDTO getByUserId(int userId);
	
	/**
	 * @return list of most active customers
	 */
	List<CustomerDTO> getTopCustomers();
	
	/**
	 * searches for a specific customer by email
	 * @param email of a customer that we're searching for
	 * @return instance of CustomerDTO
	 */
	CustomerDTO getByEmail(String email);

}
