package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.ProductDao;
import ru.alikhano.cyberlife.model.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		
	}

	@Override
	public void deleteProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(product);
		session.flush();
		
	}

	@Override
	public void updateProduct(Product product) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		
	}

	@Override
	public Product getProductById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Product product = (Product) session.get(Product.class,id);
		session.flush();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByCategory(String category) {
		
		try {
			Session session = sessionFactory.getCurrentSession();
			List<Product> listCat = session.createQuery("from Product where category = :category").setParameter("category", category).getResultList();
			return listCat;
		}
		catch (NoResultException noResultExc) {
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.getCurrentSession();
		List<Product> listAll = session.createQuery("from Product").getResultList();
		return listAll;
	}

}
