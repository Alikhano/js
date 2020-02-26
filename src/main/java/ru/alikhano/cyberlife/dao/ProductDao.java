package ru.alikhano.cyberlife.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Product;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface ProductDao extends GenericDao<Product> {

	Product getByModel(String model);

	List<Product> searchParam(String model, Integer category, Integer consLevel, Double toPrice, Double fromPrice);

	List<Product> getTopProducts();

	Product selectForUpdate(Integer id);

	void merge(Product product);


}
