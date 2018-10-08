package ru.alikhano.cyberlife.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;
import ru.alikhano.cyberlife.service.ProductJsonSender;

@RestController
public class ProductJsonSenderController {
	
	@Autowired
	ProductJsonSender productJsonSender;
	
	@GetMapping(value="/topProducts")
	public List<ProductJson> sendTopProductsGet() {
		return productJsonSender.sendProductJson();
	}

}
