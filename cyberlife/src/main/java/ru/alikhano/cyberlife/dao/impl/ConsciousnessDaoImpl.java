package ru.alikhano.cyberlife.dao.impl;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.ConsciousnessDao;
import ru.alikhano.cyberlife.model.Consciousness;

@Repository
@Transactional
public class ConsciousnessDaoImpl extends GenericDaoImpl<Consciousness> implements ConsciousnessDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Consciousness getConsByLevel(String level) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Consciousness) session.createQuery("from Consciousness where lower(level) like :level").setParameter("level", "%"+level.toLowerCase()+"%").getSingleResult();
		    
		}
		catch (NoResultException noResultExc) {
			return null;
		}
	}
}
