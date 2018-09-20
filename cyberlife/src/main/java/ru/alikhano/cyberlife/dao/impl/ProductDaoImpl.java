package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
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

	@Override
	public List<Product> searchParam(int category, int consLevel, double fromPrice, double toPrice) {
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder searchCriteriaBuilder = session.getCriteriaBuilder();

		CriteriaQuery<Product> searchCriteria = searchCriteriaBuilder.createQuery(Product.class);
		Root<Product> root = searchCriteria.from(Product.class);
		 
		searchCriteria.select(root);
		
		if (category != 0) {
			searchCriteria.where(searchCriteriaBuilder.equal(root.get("category"), category));
		}
		if (consLevel != 0) {
			searchCriteria.where(searchCriteriaBuilder.equal(root.get("cons"), consLevel));
		}

		if(fromPrice != 0 && toPrice != 0) {
			searchCriteria.where(searchCriteriaBuilder.between(root.get("price"), fromPrice, toPrice));
		}
		
	
		return session.createQuery(searchCriteria).getResultList();
	}



}
