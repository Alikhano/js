package ru.alikhano.cyberlife.dao.impl;


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
	private SessionFactory sessionFactory;

	@Override
	public Category getByType(String catType) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Category) session.createQuery("from Category where lower(catType) like :catType")
					.setParameter("catType", "%"+catType.toLowerCase()+"%").getSingleResult();
		    
		}
		catch (NoResultException noResultExc) {
			return null;
		}
	}

	@Override
	public Integer createAndGetId(Category category)  {
		return (Integer) sessionFactory.getCurrentSession().save(category);
	}

}
