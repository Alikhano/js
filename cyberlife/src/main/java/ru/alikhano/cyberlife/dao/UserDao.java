package ru.alikhano.cyberlife.dao;

import org.springframework.stereotype.Repository;

import ru.alikhano.cyberlife.model.User;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Repository
public interface UserDao extends GenericDao<User> {

	User getByUsername(String username);

}
