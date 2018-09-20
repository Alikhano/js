package ru.alikhano.cyberlife.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Product;

@Repository
public interface ProductDao extends GenericDao<Product> {
	
	Product getByModel(String model);
	
	List<Product> searchParam(int category, int consLevel, double toPrice, double fromPrice);

}
