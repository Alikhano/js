package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.GenericDao;

@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	
	private Class<T> entityClass;
	
	@Autowired
	SessionFactory sessionFactory;
	
	public GenericDaoImpl() {
	
		this.entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDao.class);
	}

	@Override
	public void create(T entity) {
		sessionFactory.getCurrentSession().save(entity);
		
	}

	@Override
	public T getById(Integer id) {
		return sessionFactory.getCurrentSession().find(entityClass, id);
	}

	@Override
	public void update(T entity) {
		sessionFactory.getCurrentSession().update(entity);
		
	}

	@Override
	public void delete(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + entityClass.getName()).getResultList();
	}

}
