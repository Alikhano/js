package ru.alikhano.cyberlife.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import ru.alikhano.cyberlife.DTO.CustomLogicException;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.DTO.SearchRequest;
import ru.alikhano.cyberlife.model.Product;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface ProductService {
	
	/**
	 * searches for a product by id
	 * @param id of a product we're searching for
	 * @return instance of Product with corresponding id
	 */
	Product getProductById(int id);

	/**
	 * @return list of all products
	 */
	List<ProductDTO> getAll();

	/**
	 * similar to getProductById(int id) method, but returns a DTO
	 * @param id of a product we're searching for
	 * @return instance of ProductDTO with corresponding id
	 * @throws CustomLogicException
	 */
	ProductDTO getById(int id) throws CustomLogicException;

	/**
	 * creates new product
	 * @param productDTO
	 * @throws CustomLogicException
	 */
	void create(ProductDTO productDTO) throws CustomLogicException;

	/** 
	 * updates an existing product entry
	 * @param productDTO
	 * @return operation status (success/error message)
	 * @throws CustomLogicException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	String update(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException;

	/**
	 * deletes a product from the databse
	 * @param productDTO product to be deleted
	 * @return operation status (success/error message)
	 * @throws CustomLogicException
	 * @throws IOException
	 * @throws TimeoutException
	 */
	String delete(ProductDTO productDTO) throws CustomLogicException, IOException, TimeoutException;
	
	/**
	 * searches for a product by model 
	 * @param model of product we're searching for
	 * @return instance of a ProductDTO witj a corresponding model
	 * @throws CustomLogicException
	 */
	ProductDTO getByModel(String model) throws CustomLogicException;
	
	/**
	 * searches for a product by a search request
	 * @param request search request
	 * @return list of products that correspond the request
	 * @throws CustomLogicException
	 */
	List<ProductInfo> searchParam(SearchRequest request) throws CustomLogicException;
	
	/**
	 * @return a list of top 10 most purchased products
	 */
	List<ProductDTO> getTopProducts();
	
	/**
	 * enables safe update of the number of units in stock for a specific product
	 * @param id of a product 
	 * @return instance of ProductDTO with corresponding id
	 */
	ProductDTO selectForUpdate(int id);
	
	/**
	 * checks whether product is in top 10
	 * @param productDTO
	 * @return true/false depending on whether product is in top 10 list
	 */
	boolean isInTop(ProductDTO productDTO);
	
	void merge(ProductDTO productDTO) throws IOException, TimeoutException;
	
	/** checks whether product is not present in any unfinished orders and can be safely deleted
	 * @param productDTO
	 * @return true/false
	 */
	boolean canBeDeleted(ProductDTO productDTO);


}
