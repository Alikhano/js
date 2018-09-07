package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.model.Product;

public interface ProductService {


	List<ProductDTO> getProductDTOList();

	ProductDTO getProductDTOById(int id);

	void addProduct(ProductDTO productDTO);

	void editProduct(ProductDTO productDTO);

	void deleteProduct(ProductDTO productDTO);

}
