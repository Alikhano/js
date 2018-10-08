package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;

public interface ProductJsonService {
	
	List<ProductJson> getTopProducts();

}
