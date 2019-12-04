package ru.alikhano.cyberlife.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.config.rabbitmq.ProductJson;
import ru.alikhano.cyberlife.service.ProductJsonService;
import ru.alikhano.cyberlife.service.ProductService;

@Service
public class ProductJsonServiceImpl implements ProductJsonService {
	
	@Autowired
	private ProductService productService;

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<ProductJson> getTopProducts() {
		List<ProductDTO> products = productService.getTopProducts();
		List<ProductJson> productsJson = new ArrayList<>();
		
		for (ProductDTO product : products) {
			ProductJson productJson = new ProductJson();
			productJson.setProductId(product.getProductId());
			productJson.setModel(product.getModel());
			productJson.setCatType(product.getCategory().getCategoryType());
			productJson.setConsLevel(product.getCons().getLevel());
			productJson.setPrice(product.getPrice());
			productsJson.add(productJson);
			
		}
		return productsJson;
	}

}
