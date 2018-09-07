package ru.alikhano.cyberlife.dao;


import java.util.List;

import ru.alikhano.cyberlife.model.Product;

public interface ProductDao {
	
	List<Product> getProductList();

	Product getProductById(int id);

	void addProduct(Product product);

	void editProduct(Product product);

	void deleteProduct(Product product);

}
