package ru.alikhano.cyberlife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.RoleDao;
import ru.alikhano.cyberlife.model.Role;
import ru.alikhano.cyberlife.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDao roleDao;

	@Override
	@Transactional
	public Role getRole() {
		return roleDao.getRole();
	}

}
