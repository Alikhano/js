package ru.alikhano.cyberlife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;
import ru.alikhano.cyberlife.service.ProductJsonSender;
import ru.alikhano.cyberlife.service.ProductJsonService;

@Service
public class ProductJsonSenderImpl implements ProductJsonSender {
	
	@Autowired
	private ProductJsonService productJsonService;

	@Override
	public List<ProductJson> sendProductJson() {
		
		return productJsonService.getTopProducts();
	}
}
