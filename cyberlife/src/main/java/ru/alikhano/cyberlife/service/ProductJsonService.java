package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface ProductJsonService {
	
	/**
	 * retrieves a list of top 10 Product instances and converts it to JSON
	 * @return list of top 10 products as JSON objects
	 */
	List<ProductJson> getTopProducts();

}
