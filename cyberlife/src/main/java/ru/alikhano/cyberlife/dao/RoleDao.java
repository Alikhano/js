package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.Role;

@Repository
public interface RoleDao {
	
	Role getRole();

}
