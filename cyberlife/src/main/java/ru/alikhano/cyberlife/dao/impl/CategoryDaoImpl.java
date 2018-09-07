package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.model.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategoryList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Category").getResultList();
	}

	@Override
	public Category getCategoryById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Category category = session.get(Category.class, id);
		session.flush();
		return category;
	}

	@Override
	public Category getCategoryByType(String catType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Category) session.createQuery("from Category where catType = :catType").setParameter("catType", catType).getSingleResult();
		    
		}
		catch (NoResultException noResultExc) {
			return null;
		}
	}

	@Override
	public void addCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
		session.flush();	
	}

}
