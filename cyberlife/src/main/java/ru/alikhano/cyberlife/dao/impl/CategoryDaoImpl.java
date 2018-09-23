package ru.alikhano.cyberlife.dao.impl;


import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.CategoryDao;
import ru.alikhano.cyberlife.model.Category;

@Repository
public class CategoryDaoImpl extends GenericDaoImpl<Category> implements CategoryDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Category getByType(String catType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Category) session.createQuery("from Category where catType = :catType").setParameter("catType", catType).getSingleResult();
		    
		}
		catch (NoResultException noResultExc) {
			return null;
		}
	}

}
