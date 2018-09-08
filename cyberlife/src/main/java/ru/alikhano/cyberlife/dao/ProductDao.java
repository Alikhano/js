package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Product;

@Repository
public interface ProductDao extends GenericDao<Product> {
	
	Product getByModel(String model);

}
