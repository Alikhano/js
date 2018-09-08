package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.User;

@Repository
public interface UserDao extends GenericDao<User> {
	
	User getByUsername(String username);

}
