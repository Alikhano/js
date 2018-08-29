package ru.alikhano.cyberlife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.model.Product;
import ru.alikhano.cyberlife.service.ProductService;

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);
		
	}

	@Override
	public void deleteProduct(Product product) {
		productDao.deleteProduct(product);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
		
	}

	@Override
	public Product getProductById(int id) {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		return productDao.getProductByCategory(category);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

}
