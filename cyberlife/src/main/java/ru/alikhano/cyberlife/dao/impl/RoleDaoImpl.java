package ru.alikhano.cyberlife.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.dao.RoleDao;
import ru.alikhano.cyberlife.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role getRole() {
		return (Role) sessionFactory.getCurrentSession().createQuery("from Role where type = :type")
				.setParameter("type", "ROLE_USER").uniqueResult();
	}

}
