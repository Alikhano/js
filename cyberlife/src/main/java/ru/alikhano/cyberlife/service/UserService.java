package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.DTO.UserDTO;
import ru.alikhano.cyberlife.model.User;

/**
 * @author Anastasia Likhanova
 * @version 1.0
 * @since 28.08.2018
 *
 */
@Service
public interface UserService {
	
	/** 
	 * creates a new user
	 * @param user
	 */
	void create(User user);
	
	/**
	 * deletes a user
	 * @param user entry to be deleted
	 */
	void delete(User user);
	
	/**
	 * searches for a user by id
	 * @param id
	 * @return User instance with corresponding id
	 */
	User getById(int id);
	
	/**
	 * @return all users
	 */
	List<User> getAll();
	
	/**
	 * updates an existing user entry
	 * @param userDTO entry to be updated
	 */
	void update(UserDTO userDTO);
	
	/**
	 * searches for a user by username
	 * @param username
	 * @return User instance with corresponding username
	 */
	User getByUsername(String username);
	
	/**
	 * searches for a user by username
	 * @param username
	 * @return UserDTO instance with corresponding username
	 */
	UserDTO getByUsernameDTO(String username);
	
	/**
	 * registers a new user
	 * @param userDTO
	 */
	void register(UserDTO userDTO);
	
	/**
	 * checks whether a password entered by client matches the one stored in the database
	 * @param password
	 * @param id user id
	 * @return true/false
	 */
	boolean verifyPassword(String password, int id);
	
	/**
	 * assigns a new password to the user
	 * @param password new password
	 * @param userDTO user instances that is assigned with a new password
	 */
	void changePassword(String password, UserDTO userDTO);
	
	/**
	 * searches for a user by id
	 * @param id
	 * @return UserDTO instance with corresponding id
	 */
	UserDTO getDTOById(int id);

}
