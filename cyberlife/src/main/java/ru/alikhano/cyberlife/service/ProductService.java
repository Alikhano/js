package ru.alikhano.cyberlife.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.model.Product;

public interface ProductService {
	
	Product getProductById(int id);


	List<ProductDTO> getAll();

	ProductDTO getById(int id) throws CustomLogicException;

	void create(ProductDTO productDTO) throws CustomLogicException;

	String update(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException;

	String delete(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException;
	
	ProductDTO getByModel(String model) throws CustomLogicException;
	
	List<ProductInfo> searchParam(String model, int category, int consLevel, double fromPrice, double toPrice) throws CustomLogicException;
	
	List<ProductDTO> getTopProducts();
	
	ProductDTO selectForUpdate(int id);
	
	boolean isInTop(ProductDTO productDTO);
	
	void merge(ProductDTO productDTO) throws IOException, TimeoutException;
	
	boolean canBeDeleted(ProductDTO productDTO);


}
