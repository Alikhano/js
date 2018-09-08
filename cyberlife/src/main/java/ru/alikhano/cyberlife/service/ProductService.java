package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.ProductDTO;

public interface ProductService {

	List<ProductDTO> getAll();

	ProductDTO getById(int id);

	void create(ProductDTO productDTO);

	void update(ProductDTO productDTO);

	void delete(ProductDTO productDTO);
	
	ProductDTO getByModel(String model);

}
