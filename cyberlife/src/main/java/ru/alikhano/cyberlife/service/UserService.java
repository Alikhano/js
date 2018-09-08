package ru.alikhano.cyberlife.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.alikhano.cyberlife.model.User;

@Service
public interface UserService {
	
	void create(User user);
	
	void delete(User user);
	
	User getById(int id);
	
	List<User> getAll();
	
	void update(User user);
	
	User getByUsername(String username);

}
