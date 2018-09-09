package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.AddressDTO;

@Service
public interface AddressService {
	
	List<AddressDTO> getAll();

	AddressDTO getById(int id);

	void create(AddressDTO addressDTO);

	void update(AddressDTO addressDTO);

	void delete(AddressDTO addressDTO);

}
