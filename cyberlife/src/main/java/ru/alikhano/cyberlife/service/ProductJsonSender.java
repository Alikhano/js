package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
public interface ProductJsonSender {
	
	/**
	 * @return list of top 10 Product instances as JSON objects
	 */
	List<ProductJson> sendProductJson();

}
