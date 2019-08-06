package ru.alikhano.cyberlife.controller.admin;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;
import ru.alikhano.cyberlife.service.ProductJsonSender;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@RestController
public class ProductJsonSenderController {
	
	@Autowired
	private ProductJsonSender productJsonSender;
	
	private static final Logger logger = LogManager.getLogger(ProductJsonSenderController.class);
	
	/**
	 * @return a list of top 10 products as JSON
	 */
	@GetMapping(value="/topProducts")
	public List<ProductJson> sendTopProductsGet() {
		logger.info("sent to 10 products to cyberdisplay");
		return productJsonSender.sendProductJson();
	}

}
