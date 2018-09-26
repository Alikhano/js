package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.model.Product;

public interface ProductService {
	
	/*List<Product> getAllProducts();
	void create(Product product);
	void delete(Product product);
	void update(Product product);
	*/
	
	Product getProductById(int id);


	List<ProductDTO> getAll();

	ProductDTO getById(int id);

	void create(ProductDTO productDTO) throws CustomLogicException;

	void update(ProductDTO productDTO);

	void delete(ProductDTO productDTO) throws CustomLogicException;
	
	ProductDTO getByModel(String model) throws CustomLogicException;
	
	List<ProductInfo> searchParam(int category, int consLevel, double fromPrice, double toPrice) throws CustomLogicException;
	
	List<ProductDTO> getTopProducts();
	
	ProductDTO selectForUpdate(int id);


}
