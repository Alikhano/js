package ru.alikhano.cyberlife.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Product;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface ProductDao extends GenericDao<Product> {
	
	/** 
	 * searches for specific product entry by model
	 * @param model product model to search in database
	 * @return instance of User with corresponding model
	 */
	Product getByModel(String model);
	
	/** 
	 * searches for specific products by a set of parameters
	 * @param model product model number
	 * @param category 
	 * @param consLevel level of consciousness 
	 * @param fromPrice minimum price of a product
	 * @param toPrice maximum price of a product
	 * @return list of products that fit the search request
	 */
	List<Product> searchParam(String model, int category, int consLevel, double toPrice, double fromPrice);
	
	/** 
	 * select the most purchased/top 10 products
	 * @return list of top 10 products
	 */
	List<Product> getTopProducts();
	
	/** 
	 * enables save update of a number of units in stock during purchase
	 * @param id of a product entry to be modified
	 * @return instance of Product with corresponding id
	 */
	Product selectForUpdate(Integer id);
	
	/** 
	 * saves an updated product entry
	 * @param product instance of Product to be updated
	 */
	void merge(Product product);


}
