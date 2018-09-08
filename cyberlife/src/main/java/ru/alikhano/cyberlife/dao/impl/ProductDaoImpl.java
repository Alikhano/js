package ru.alikhano.cyberlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.model.Product;

@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product> implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Product getByModel(String model) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where model =:model").setParameter("model", model).uniqueResult();
	}



}
