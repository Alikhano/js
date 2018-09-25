package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.CustomerDTO;

@Service
public interface CustomerService {
	
	List<CustomerDTO> getAll();

	CustomerDTO getById(int id);

	void create(CustomerDTO customerDTO);

	void update(CustomerDTO customerDTO) throws CustomLogicException;

	void delete(CustomerDTO customerDTO);
	
	CustomerDTO getByUserId(int userId);
	
	List<CustomerDTO> getTopCustomers();
	
	CustomerDTO getByEmail(String email);

}
