package ru.alikhano.cyberlife.service;

import java.util.List;

import ru.alikhano.cyberlife.model.Product;

public interface ProductService {
	
void addProduct(Product product);
	
	void deleteProduct(Product product);
	
	void updateProduct(Product product);
	
	Product getProductById(int id);
	
	List<Product> getProductByCategory(String category);
	
	List<Product> getAllProducts();

}
