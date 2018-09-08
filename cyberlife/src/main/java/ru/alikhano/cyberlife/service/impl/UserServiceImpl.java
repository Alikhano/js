package ru.alikhano.cyberlife.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.alikhano.cyberlife.dao.UserDao;
import ru.alikhano.cyberlife.model.User;
import ru.alikhano.cyberlife.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	@Transactional
	public void create(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userDao.create(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public User getById(int id) {
		return userDao.getById(id);
	}

	@Override
	@Transactional
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	@Transactional
	public User getByUsername(String username) {
		return userDao.getByUsername(username);
	}

}
