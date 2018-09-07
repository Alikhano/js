package ru.alikhano.cyberlife.dao.impl;

import java.util.List;

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
public class ConsciousnessDaoImpl implements ConsciousnessDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Consciousness> getConList() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Consciousness").getResultList();
	}

	@Override
	public Consciousness getConsById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Consciousness cons = session.get(Consciousness.class, id);
		session.flush();
		return cons;
	}

	@Override
	public Consciousness getConsByLevel(String level) {
		try {
			Session session = sessionFactory.getCurrentSession();
			return (Consciousness) session.createQuery("from Consciousness where level = :level").setParameter("level", level).getSingleResult();
		    
		}
		catch (NoResultException noResultExc) {
			return null;
		}
	}

	@Override
	public void addLevel(Consciousness consciousness) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(consciousness);
		session.flush();	
	}
}
